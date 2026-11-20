
const nodemailer = require("nodemailer");
const dotenv = require("dotenv");
const path = require("path");

// Load environment variables
dotenv.config({
    path: path.join(__dirname, "../.env")
});

// Email details
const senderEmail = process.env.EMAIL_USER;
const senderPassword = process.env.EMAIL_PASS;
const receiverEmail = process.env.TEST_TO;

async function sendEmail() {
    try {
        // Create Gmail transporter
        const transporter = nodemailer.createTransport({
            service: "gmail",
            auth: {
                user: senderEmail,
                pass: senderPassword
            }
        });

        // Image attachment
        const imagePath = path.join(__dirname, "profile.jpg");

        // Email configuration
        const mailOptions = {
            from: senderEmail,
            to: receiverEmail,
            subject: "Challenge 3 Completed",
            text: `
Hello,

Name: Aditya Kaushik
Semester: 7th
Branch: Computer Science and Engineering
Roll Number: 230180101001

Please find my image attached.

Regards,
Aditya Kaushik
            `,
            attachments: [
                {
                    filename: "profile.jpg",
                    path: imagePath
                }
            ]
        };

        // Send email
        const info = await transporter.sendMail(mailOptions);

        console.log("Email sent successfully!");
        console.log("Message ID:", info.messageId);

    } catch (error) {
        console.error("Error sending email:", error.message);
    }
}

// Validate configuration
if (!senderEmail || !senderPassword || !receiverEmail) {
    console.log("Please configure EMAIL_USER, EMAIL_PASS and TEST_TO in .env");
} else {
    sendEmail();
}
