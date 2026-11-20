const nodemailer = require("nodemailer");
const path = require("path");
require("dotenv").config({ path: path.join(__dirname, ".env") });

const allowedFormats = [".png", ".jpg", ".jpeg"];

async function sendMail() {
  const user = process.env.EMAIL_USER;
  const pass = process.env.EMAIL_PASS;
  const to = process.env.RECIPIENT_EMAIL || "hr@ignitershub.com";

  if (!user || !pass) {
    console.log("Please set EMAIL_USER and EMAIL_PASS in .env file");
    return;
  }

  const imagePath = path.join(__dirname, "attachment.png");
  const ext = path.extname(imagePath).toLowerCase();

  if (!allowedFormats.includes(ext)) {
    console.log("Only PNG, JPG and JPEG image types are allowed");
    return;
  }

  const transporter = nodemailer.createTransport({
    service: "gmail",
    auth: { user, pass },
  });

  const mailOptions = {
    from: user,
    to: to,
    subject: "Challenge 3 Completed",
    text: `Name: Ayush Sharma
Semester: 7th Semester
Branch: Computer Science & Engineering
Roll Number: 230180101009`,
    attachments: [
      {
        filename: "attachment.png",
        path: imagePath,
      },
    ],
  };

  try {
    const info = await transporter.sendMail(mailOptions);
    console.log("Email sent: " + info.response);
  } catch (err) {
    console.error("Error sending email:", err.message);
  }
}

sendMail();
