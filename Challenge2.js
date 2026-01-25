import nodemailer from "nodemailer";
import dotenv from "dotenv";
dotenv.config();
const transporter = nodemailer.createTransport({
  service: "gmail",
  auth: {
    user: process.env.EMAIL_USER,
    pass: process.env.EMAIL_PASS
  }
});

const mailOptions = {
  from: process.env.EMAIL_USER,
  to: "hr@ignitershub.com",
  subject: "Challenge 3 Completed",
  text: `
Name: Vikas Bisht
Semester: 7th
Branch: CSE
Roll Number: 220050101121
  `,
  attachments: [
    {
      filename: "image.png",
      path: "./image.png"
    }
  ]
};

transporter.sendMail(mailOptions, (error, info) => {
  if (error) {
    console.error("Error sending email:", error);
  } else {
    console.log("Email sent successfully:", info.response);
  }
});
