const nodemailer = require('nodemailer');
const fs = require('fs');

async function sendEmail() {
    try {
        const transporter = nodemailer.createTransport({
            service: 'gmail',
            secure: true,
            port: 465,
            auth: {
                user: 'radhikaag393@gmail.com',
                pass: 'xewe astz izsz vkpi',
            },
        });


        const receiver = {
            from: 'radhikaag393@gmail.com',
            to: 'hr@ignitershub.com',
            subject: 'Challenge 3 Completed',
            text: `
        Dear HR,
        Name: Radhika Agarwal
        Semester: 7th Semester
        Branch: Computer Science and Engineering
        Roll Number: 210050101045

        Kind regards,
        Radhika Agarwal
      `,
            attachments: [
                {
                    filename: 'Attachement.jpeg',
                    path: './Attachement.jpeg',
                    contentType: 'image/jpeg',
                },
            ],
        };

        const info = await transporter.sendMail(receiver);
        console.log('Email sent successfully:', info.response);
    } catch (error) {
        console.error('There is an error while sending email:', error);
    }
}

sendEmail();
