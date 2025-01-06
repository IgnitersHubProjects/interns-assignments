import tkinter as tk
from tkinter import filedialog, messagebox
import difflib

class ApproximateSearchApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Approximate Search Tool")
        self.root.geometry("600x400")

        # Variables
        self.word_list = []
        self.k = 3  # Number of suggestions

        # GUI Components
        self.create_widgets()

    def create_widgets(self):
        # File Selection Button
        self.file_button = tk.Button(self.root, text="Load Word File", command=self.load_word_file, bg="lightblue")
        self.file_button.pack(pady=10)

        # Input Label and Entry
        self.input_label = tk.Label(self.root, text="Enter a word to search:")
        self.input_label.pack(pady=5)
        self.input_entry = tk.Entry(self.root, width=40)
        self.input_entry.pack(pady=5)

        # Search Button
        self.search_button = tk.Button(self.root, text="Search", command=self.search_word, bg="lightgreen")
        self.search_button.pack(pady=10)

        # Output Text Area
        self.output_label = tk.Label(self.root, text="Suggestions:")
        self.output_label.pack(pady=5)
        self.output_text = tk.Text(self.root, height=10, width=50, state=tk.DISABLED)
        self.output_text.pack(pady=10)

    def load_word_file(self):
        # Open a file dialog to select the file
        file_path = filedialog.askopenfilename(filetypes=[("Text Files", "*.txt")])
        if file_path:
            try:
                with open(file_path, 'r') as file:
                    self.word_list = [line.strip() for line in file.readlines()]
                messagebox.showinfo("Success", "Word file loaded successfully!")
            except Exception as e:
                messagebox.showerror("Error", f"Could not load file: {str(e)}")
        else:
            messagebox.showwarning("Warning", "No file selected.")

    def search_word(self):
        # Get input word
        input_word = self.input_entry.get().strip()
        if not input_word:
            messagebox.showwarning("Warning", "Please enter a word to search.")
            return

        if not self.word_list:
            messagebox.showwarning("Warning", "Please load a word file first.")
            return

        # Perform approximate search
        similar_words = self.get_top_k_similar_words(input_word, self.word_list, self.k)

        # Display results
        self.output_text.config(state=tk.NORMAL)
        self.output_text.delete(1.0, tk.END)
        if similar_words:
            self.output_text.insert(tk.END, "\n".join(similar_words))
        else:
            self.output_text.insert(tk.END, "No suggestions found.")
        self.output_text.config(state=tk.DISABLED)

    def get_top_k_similar_words(self, input_word, word_list, k):
        similarity_scores = [(word, difflib.SequenceMatcher(None, input_word, word).ratio()) for word in word_list]
        similarity_scores.sort(key=lambda x: x[1], reverse=True)
        return [item[0] for item in similarity_scores[:k]]

# Run the app
if __name__ == "__main__":
    root = tk.Tk()
    app = ApproximateSearchApp(root)
    root.mainloop()
