import express from 'express'
import dotenv from 'dotenv'
import { sendEmail } from './emailService.js'

dotenv.config()

const app = express()

app.get('/', (req, res) => {
  res.send('Hello World')
})

app.get('/send-email', async (req, res) => {
  try {
    const emailBody = `
      Dear HR,

      Challenge 3 Completed.

      Name: 'Reesav Gupta'
      Semester: '8th'
      Branch: 'CSE'
      Roll Number: '21CSEC08'
      college: 'SIST Chisopani'
      
      Best regards,
      Reesav Gupta
    `
    await sendEmail(emailBody)
    res.json({ success: true, message: 'Email sent successfully' })
  } catch (error) {
    console.error('Error sending email:', error)
    res
      .status(500)
      .json({ success: false, message: 'Failed to send email', error })
  }
})

const PORT = 3000
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`)
})
