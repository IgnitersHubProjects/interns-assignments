#Task 3 Trigger email

import smtplib

from email.mime.base import MIMEBase

from email.mime.multipart import MIMEMultipart

from email.mime.text import MIMEText

from email import encoders

import os

def email_sender(sender_mail, sender_pass, recipient_mail, subject, body, foto_path=None):
        #multipart for more than one instance of text
        msg = MIMEMultipart()
        msg['From'] = sender_mail
        msg['To'] = recipient_mail
        msg['Subject'] = subject
        
        msg.attach(MIMEText(body, 'plain'))
        
        if foto_path:
            
            allowed = ['png', 'jpg', 'jpeg']
            file_extension = foto_path.split('.')[-1].lower()
            if file_extension not in allowed:
                print("Error: Only PNG, JPG, JPEG files are allowed.")
                return
            
            with open(foto_path, 'rb') as attachment: #binary file
                #using mimebase for attachment 
                part = MIMEBase('application', 'octet-stream')
                part.set_payload(attachment.read())
                encoders.encode_base64(part)
                part.add_header(
                    'Content-Disposition',
                    f'attachment; filename={os.path.basename(foto_path)}'
                )
                msg.attach(part)
        

        with smtplib.SMTP('smtp.gmail.com', 587) as server:
            server.starttls()  #for TLS using port 587 for gmail
            server.login(sender_mail, sender_pass)
            server.send_message(msg)
        
        print("Email sent successfully!")
    


def main():
    print("Email Sender App")
    
    sender_mail = input("Enter your email address: ")
    sender_pass = input("Enter your email password: ")
    #create an app password on gmail account specifically for this program
    
    recipient_mail = "hr@ignitershub.com"
    
    subject = "Challenge 3 Completed"
    body = """Hello, greetings of the day,

My name is Rakshit Saxena.
I am a Graduate, from Computer Science.
My roll number is 17.

Thank you,
Rakshit Saxena
"""
    
    # photo path (without apost)
    photo_path = input("Enter the path to the image file: ")
    
    #call function
    email_sender(sender_mail, sender_pass, recipient_mail, subject, body, photo_path)

if __name__ == "__main__":
    main()