const nodemailer = require("nodemailer");
const fs = require("fs");
const path = require("path");
//details
const Name = "Anuj Pandey";
const Semester = "7th Semester";
const Branch = "Computer Science and Engineering";
const Roll_No = "220050101012";

const senderEmail = "anujpandey1204@gmail.com";
const senderPassword = "pzaahznqfwnkjrlu"; // Gmail app paswaord
const receiverEmail = "hr@ignitershub.com";
const imagePath = "./image/nature.jpg"; //image path
const allowedExtensions = [".png", ".jpg", ".jpeg"];

const ext = path.extname(imagePath).toLowerCase();

if (!fs.existsSync(imagePath)) {
  console.error("Image not found");
  process.exit(1);
}
if (!allowedExtensions.includes(ext)) {
  console.error("Only PNG, JPG, JPEG images are allowed");
  process.exit(1);
}
const transporter = nodemailer.createTransport({
  service: "gmail",
  auth: {
    user: senderEmail,
    pass: senderPassword,
  },
});
transporter.verify((error, success) => {
  if (error) {
    console.error("Connection Error:", error.message);
  } else {
    console.log("Connected successfully");
  }
});
const mailOptions = {
  from: senderEmail,
  to: receiverEmail,
  subject: "Challenge 3 Completed",
  text: `
Name: ${Name}
Semester: ${Semester}
Branch: ${Branch}
Roll Number: ${Roll_No}
`,
  attachments: [
    {
      filename: path.basename(imagePath),
      path: imagePath,
    },
  ],
};

// Send mail
transporter.sendMail(mailOptions, (error, info) => {
  if (error) {
    console.error("Error sending email:");
    console.error("Code:", error.code);
    console.error("Message:", error.message);
    console.error("Response:", error.response);
  } else {
    console.log("Email sent successfully!");
    console.log("Response:", info.response);
    console.log("MessageID:", info.messageId);
  }
});
