const nodemailer = require('nodemailer');
const path = require('path');
require('dotenv').config();

const { EMAIL_USER, EMAIL_PASS } = process.env;
const toEmail = 'hr@ignitershub.com';
const imagePath = 'C:\\Internship\\Prog 3\\testimage.png';

if (!['.png', '.jpg', '.jpeg'].includes(path.extname(imagePath).toLowerCase())) {
  console.log('Invalid image type. Only PNG, JPG, and JPEG are allowed.');
  process.exit();
}

const transporter = nodemailer.createTransport({
  host: 'smtp.gmail.com',
  port: 587,
  secure: false,
  auth: { user: EMAIL_USER, pass: EMAIL_PASS },
  tls: { rejectUnauthorized: false }
});

transporter.sendMail({
  from: EMAIL_USER,
  to: toEmail,
  subject: 'Challenge 3 Completed',
  text: `Hello,

THIS IS A TEST FOR THE CHALLENGE 3

Name: Tejaswi Bali
Semester: 5
Roll No: 22215125
Branch: BCA  

Yours sincerely,
Tejaswi Bali`,
  attachments: [{ filename: path.basename(imagePath), path: imagePath }]
}, (error, info) => {
  if (error) {
    console.log('Error occurred:', error);
  } else {
    console.log('Email sent:', info.response);
  }
});
