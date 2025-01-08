const nodemailer = require("nodemailer");
const fs = require("fs");
const path = require("path");

// Email configuration
const EMAIL_USER = "arjunyakthumba9@gmail.com"; 
const EMAIL_PASSWORD = "lctd jtfq zest zcih"; 

// Sender details
const senderDetails = {
    name: "Arjun Limboo",
    semester: "8th",
    branch: "Computer Engineering",
    rollNumber: "21CSEC38",
};

// Image file path
const imagePath = path.join(__dirname, "ss.png");

async function sendEmail() {
    try {
        // Check if the image file exists
        if (!fs.existsSync(imagePath)) {
            throw new Error("Image file not found. Ensure the file exists in the current directory.");
        }

        // Create the email transporter
        const transporter = nodemailer.createTransport({
            service: "gmail",
            auth: {
                user: EMAIL_USER,
                pass: EMAIL_PASSWORD, // Use app password if 2FA is enabled
            },
        });

        // Email content
        const mailOptions = {
            from: EMAIL_USER,
            to: "hr@ignitershub.com", // Receiver email
            subject: "Challenge 3 Completed",
            text: `Hello Sir/Ma'am,

My name is ${senderDetails.name}. I am currently in ${senderDetails.semester} semester, studying in the ${senderDetails.branch} branch. My roll number is ${senderDetails.rollNumber}.

Thank you!
`,
            attachments: [
                {
                    filename: path.basename(imagePath), 
                    path: imagePath,
                },
            ],
        };

        // Send the email
        const info = await transporter.sendMail(mailOptions);
        console.log("Email sent successfully: ", info.response);
    } catch (error) {
        console.error("Error sending email: ", error.message);
    }
}

// Call the function to send the email
sendEmail();
