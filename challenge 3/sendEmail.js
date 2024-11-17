const nodemailer = require('nodemailer');
const fs = require('fs');
const path = require('path');

// Sender and receiver information
const senderEmail = 'komalkafaltiya2555@gmail.com';
const senderPassword = 'password';      // due to security reaosn i am not putting my email address here
const receiverEmail = 'hr@ignitershub.com';

// Email content
const subject = 'Challenge 3 Completed';
const body = `
Name: Komal Kafaltiya
Semester: 4
Branch: CS-IT
Roll Number: 21256342
`;

const imagePath = './komal_kafaltiya.png';
const validImageTypes = ['.png', '.jpg', '.jpeg'];
const imageExtension = path.extname(imagePath).toLowerCase();

if (!fs.existsSync(imagePath)) {
    console.error('Image file not found at the specified path.');
    process.exit(1);
}

if (!validImageTypes.includes(imageExtension)) {
    console.error('Invalid image type. Only PNG, JPG, JPEG are allowed.');
    process.exit(1);
}

const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: senderEmail,
        pass: senderPassword,
    },
});

const mailOptions = {
    from: senderEmail,
    to: receiverEmail,
    subject: subject,
    text: body,
    attachments: [
        {
            filename: path.basename(imagePath),
            path: imagePath,
        },
    ],
};

// Send mail with defined transport object
transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
        return console.error('An error occurred while sending the email:', error);
    }
    console.log('Email sent successfully! Message ID:', info.messageId);
});



