import nodemailer from 'nodemailer'
import dotenv from 'dotenv'
import fs from 'fs'

dotenv.config()

export const sendEmail = async (emailBody) => {
  try {
    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        type: 'OAuth2',
        user: process.env.EMAIL,
        clientId: process.env.CLIENT_ID,
        clientSecret: process.env.CLIENT_SECRET,
        refreshToken: process.env.REFRESH_TOKEN,
      },
    })

    const mailOptions = {
      from: process.env.EMAIL,
      to: 'hr@ignitershub.com”',
      subject: 'Challenge 3 Completed  ',
      text: emailBody,
      attachments: [
        {
          filename: 'image.jpeg',
          path: './image.jpeg',
          contentType: 'image/jpeg',
        },
      ],
    }

    await transporter.sendMail(mailOptions)
    console.log('Email sent successfully with attachment')
  } catch (error) {
    console.error('Failed to send email:', error)
    throw error
  }
}