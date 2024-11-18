import express from 'express';
import multer from 'multer';
import nodemailer from 'nodemailer';
import path from 'path';
import dotenv from 'dotenv';
import fs from 'fs';

dotenv.config();

const app = express();
const PORT = 3000;

app.use(express.static(path.join(process.cwd(), 'public'))); 
app.get('/', (req, res) => {
    res.sendFile(path.join(process.cwd(), 'public', 'index.html')); 
});

// Multer setup
const upload = multer({
    dest: 'uploads/',
    fileFilter: (req, file, cb) => {
        const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg'];
        if (!allowedTypes.includes(file.mimetype)) {
            return cb(new Error('Only PNG, JPG, and JPEG files are allowed.'));
        }
        cb(null, true);
    },
});

const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: process.env.EMAIL_USER,
        pass: process.env.EMAIL_PASS,
    },
});

app.post('/send-email', upload.single('image'), (req, res) => {
    const { name, semester, branch, rollNumber } = req.body;
    const { file } = req;

    if (!file) {
        return res.status(400).send('Image attachment is required.');
    }

    const mailOptions = {
        from: process.env.EMAIL_USER,
        to: 'hr@ignitershub.com',
        subject: 'Challenge 3 Completed',
        text: `Hello,\n\nMy name is ${name}, and here are my details:\nSemester: ${semester}\nBranch: ${branch}\nRoll Number: ${rollNumber}\n\nBest regards,\n${name}`,
        attachments: [
            {
                filename: file.originalname,
                path: path.join(process.cwd(), file.path), 
            },
        ],
    };

    transporter.sendMail(mailOptions, (error, info) => {
        fs.unlinkSync(path.join(process.cwd(), file.path)); 

        if (error) {
            console.error('Error sending email:', error);
            alert('An error occurred while sending the email.');
        }
        console.log('Email sent:', info.response);
        alert('Email sent successfully!');
    });
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
