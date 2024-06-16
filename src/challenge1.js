const nodemailer = require("nodemailer");

const transport = nodemailer.createTransport({
  host: "smtp-relay.brevo.com",
  port: 587,
  secure: false,
  auth: {
    user: "76c643001@smtp-brevo.com",
    pass: "n4NQS6Td5mOwFWr7",
  },
});

const mailOptions = {
  from: "shatakshishri@gmail.com",
  to: "hr@ignitershub.com",
  subject: "Challenge 3 Completed",
  text: `Name: Shatakshi Shrivastava
    Semester: 8th
    Branch: Electronics and Communications
    Roll Number: 12015119
    `,
  attachments: [
    {
      filename: "attachment.png",
      path: "./attachment.png",
    },
  ],
};

async function sendMail(){
    const result = await transport.sendMail(mailOptions)
    console.log(result);
}

sendMail()
