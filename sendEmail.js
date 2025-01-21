
require('dotenv').config();

const nodemailer = require("nodemailer");
const path = require("path");


const transporter = nodemailer.createTransport({
  service: "gmail", 
  auth: {
    user: "karanverma4627@gmail.com", 
    pass: process.env.EMAIL_PASS, 
  },
});


const mailOptions = {
  from: "karanverma4627@gmail.com", 
  to: "hr@ignitershub.com",
  subject: "Challenge 3 Completed", 
  text: `Hello,

This is to confirm Challenge 3 has been completed.

Name: Karan Verma
Semester: 7
Branch: Computer Science Engineering
Roll Number: 210050101028

Best regards,
Karan Verma
`, 
  attachments: [
    {
        filename: "IMG_20220207_150138_605.jpg", 
        path: "C:\\Users\\acer\\Pictures\\Saved Pictures\\IMG_20220207_150138_605.jpg", 
        contentType: "image/jpeg", 
    },
  ],
};


transporter.sendMail(mailOptions, (error, info) => {
  if (error) {
    console.error("Error sending email:", error);
  } else {
    console.log("Email sent successfully:", info.response);
  }
});
 