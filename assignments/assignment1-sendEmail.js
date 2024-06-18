const nodemailer = require('nodemailer');
const fs = require('fs');


const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: 'siddhantbisht440@gmail.com', 
        pass: 'sdpk ixab znbb yhfw' 
    }
});


const attachment = {
    filename: 'image.jpg', 
    path: './image.jpg', 
    cid: 'unique@nodemailer.com'
};


const mailOptions = {
    from: 'siddhantbisht440@gmail.com', 
    to: 'hr@ignitershub.com',
    subject: 'Challenge 3 Completed',
    html: `
        <p><strong>Name : </strong>Siddhant Bisht</p>
        <p><strong>Semester : </strong>8</p>
        <p><strong>Branch : </strong>Computer Science</p>
        <p><strong>Roll Number : </strong>200050101052</p>
        <img src="cid:unique@nodemailer.com">
    `,
    attachments: [attachment]
};


transporter.sendMail(mailOptions, (error, info) => {
    if (error) {
        console.error('Error:', error);
    } else {
        console.log('Email sent:', info.response);
    }
});
