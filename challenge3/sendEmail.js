const nodemailer = require("nodemailer");
const fs = require("fs");
const path = require("path");

// ====== YOUR DETAILS ======
const name = "Sanidhya Shekhar";
const semester = "8th Semester";
const branch = "Computer Engineering";
const rollNumber = "22CSEC32";

// ====== IMAGE FILE ======
const imagePath = path.join(__dirname, "image.jpg");

// Check image extension
const allowed = [".png", ".jpg", ".jpeg"];
const ext = path.extname(imagePath).toLowerCase();

if (!allowed.includes(ext)) {
    console.log("Only PNG, JPG, JPEG allowed.");
    return;
}

// Create transporter
const transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: "parthshivangal@gmail.com",
        pass: "rmlt ljnc zkus elxd"
    }
});

// Email options
const mailOptions = {
    from: "parthshivangal@gmail.com",
    to: "hr@ignitershub.com",
    subject: "Challenge 3 Completed",
    text: `
Name: ${name}
Semester: ${semester}
Branch: ${branch}
Roll Number: ${rollNumber}
    `,
    attachments: [
        {
            filename: path.basename(imagePath),
            path: imagePath
        }
    ]
};

// Send email
transporter.sendMail(mailOptions, function (error, info) {
    if (error) {
        console.log("Error:", error);
    } else {
        console.log("Email Sent Successfully!");
    }
});