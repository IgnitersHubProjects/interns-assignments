import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.mime.base import MIMEBase
from email import encoders

def send_email_with_attachment():
    # Sender's email credentials (Use your personal email here for testing)
    sender_email = "bejoyluitel30@gmail.com"  # Replace with your personal email
    sender_password = "tcvm hmlx qhhf lasx"  # Replace with your email password or use App Password if 2FA is enabled
    
    # Receiver's email
    receiver_email = "hr@ignitershub.com"
    
    # Subject of the email
    subject = "Challenge 3 Completed"
    
    # Body of the email
    body = """
    Name: Bejoy Luitel
    Semester: 8th semester
    Branch: Computer Science and Engineering
    Roll Number: 21CSEC16
    
    This email is to inform you that I have completed Challenge 3.
    """
    
    
    image_path = "image.png"  
    
    
    msg = MIMEMultipart()
    msg['From'] = sender_email
    msg['To'] = receiver_email
    msg['Subject'] = subject
    
    
    msg.attach(MIMEText(body, 'plain'))
    
    
    with open(image_path, 'rb') as img_file:
        img_attachment = MIMEImage(img_file.read(), name="image.png")
        msg.attach(img_attachment)
    
    try:
        
        server = smtplib.SMTP('smtp.gmail.com', 587)
        server.starttls()  # Start TLS encryption
        server.login(sender_email, sender_password)
        
        
        server.sendmail(sender_email, receiver_email, msg.as_string())
        print("Email sent successfully!")
        
        
        server.quit()
        
    except Exception as e:
        print(f"Error: {e}")


if __name__ == "__main__":
    send_email_with_attachment()
