

import os
import smtplib
from email.message import EmailMessage
from pathlib import Path

HR_EMAIL = "hr@ignitershub.com"

SENDER_EMAIL = os.getenv("SENDER_EMAIL")
APP_PASSWORD = os.getenv("EMAIL_APP_PASSWORD")

# the image should be in the same folder as this script
IMAGE_FILE = Path(__file__).resolve().parent / "image.png"

# details that go in the email body (change these if needed)
NAME = "MAYANK"
SEMESTER = "THIRD"
BRANCH = "MCA"
ROLL_NUMBER = "250180600008"  # TODO: put the real roll number here

# which file types we allow, and the name email needs for each
IMAGE_TYPES = {
    ".png": "png",
    ".jpg": "jpeg",
    ".jpeg": "jpeg",
}


def create_email():
    message = EmailMessage()

    message["From"] = SENDER_EMAIL
    message["To"] = HR_EMAIL
    message["Subject"] = "Challenge 3 Completed"

    message.set_content(
        f"""Hello,

Hello there my Challenge 3 submission attached.

Name: {NAME}
Semester: {SEMESTER}
Branch: {BRANCH}
Roll Number: {ROLL_NUMBER}

Regards,
{NAME}
"""
    )

    return message


def attach_image(message, image_path):
    # make sure the file is really there before we try to read it
    if not image_path.exists():
        raise FileNotFoundError(f"Attachment not found: {image_path}")

    extension = image_path.suffix.lower()

    if extension not in IMAGE_TYPES:
        raise ValueError("Only PNG and JPG images are supported.")

    with open(image_path, "rb") as file:
        image_data = file.read()

    message.add_attachment(
        image_data,
        maintype="image",
        subtype=IMAGE_TYPES[extension],
        filename=image_path.name,
    )


def send_email():
    # stop early if the credentials were never set
    if not SENDER_EMAIL or not APP_PASSWORD:
        print("Email credentials are missing.")
        print("Set SENDER_EMAIL and EMAIL_APP_PASSWORD first.")
        return

    try:
        message = create_email()
        attach_image(message, IMAGE_FILE)

        # Gmail's SMTP server, port 465 for a secure (SSL) connection
        with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
            server.login(SENDER_EMAIL, APP_PASSWORD)
            server.send_message(message)

        print("Email sent successfully!")

    except (FileNotFoundError, ValueError) as error:
        # problem with the image
        print(f"Attachment problem: {error}")

    except smtplib.SMTPAuthenticationError:
        print("Login failed. Check your email and app password.")
        print("(Gmail needs an App Password, not your normal password.)")

    except Exception as error:
        print(f"Failed to send email: {error}")


if __name__ == "__main__":
    send_email()