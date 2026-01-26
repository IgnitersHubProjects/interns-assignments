const nodemailer = require("nodemailer");
const path = require("path");
const fs = require("fs");

const imagePath = "./images/rdr.jpg";
const allowedExtensions = [".png", ".jpg", ".jpeg"];

const ext = path.extname(imagePath).toLowerCase();

if (!allowedExtensions.includes(ext)) {
  console.log("Only PNG, JPG, JPEG images are allowed.");
  process.exit(1);
}

if (!fs.existsSync(imagePath)) {
  console.log("Image file not found.");
  process.exit(1);
}

const transporter = nodemailer.createTransport({
  service: "gmail",
  auth: {
    user: "akshat24kanyal@gmail.com",
    pass: "clvx xqde hatb zxuw"
  }
});

const mailOptions = {
  from: "akshat24kanyal@gmail.com",
  to: "kanyalakshat14@gmail.com",
  subject: "Challenge 3 Completed",
  text: `
Name: Akshat Kanyal
Semester: 7
Branch: CSE
Roll Number: 220050101008
`,
  attachments: [
    {
      filename: path.basename(imagePath),
      path: imagePath
    }
  ]
};

transporter.sendMail(mailOptions, (err, info) => {
  if (err) console.log("Error:", err);
  else console.log("Email sent successfully:", info.response);
});
