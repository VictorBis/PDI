from PIL import ImageTk, Image

class Imagen():

    """
    Constructor
    """
    def __init__(self,path):
        size = 500,500
        self.original = Image.open(path)
        self.modified = Image.open(path)
        self.original.thumbnail(size,Image.ANTIALIAS)
        self.modified.thumbnail(size,Image.ANTIALIAS)

    """
    Funcion to get the original image
    """
    def getOriginal(self):
        return self.original

    """
    Funcion to get the modified image
    """
    def getModified(self):
        return self.modified
