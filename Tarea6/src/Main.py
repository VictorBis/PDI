#!/usr/bin/env python
# -*- coding: utf-8 -*-

import tkFileDialog
import types
from Tkinter import *
import tkMessageBox
from PIL import ImageTk, Image
import os
from Imagen import *
from Recursive import *


class Window(Frame):
    global image

    """
    Constructor
    """
    def __init__(self, parent = None):
        Frame.__init__(self,parent)
        self.pack(fill=BOTH, expand=True)
        self.parent = parent
        self.original = None
        self.modified = None
        self.createCanvas()
        self.createMenu()

    """
    Function to create a menu
    """
    def createMenu(self):
        self.menuBar = Menu(self.parent)
        #Options of the menu bar
        self.imageMenu = Menu(self.menuBar, tearoff=0)
        self.imageMenu.add_command(label="Seleccionar Imagen", command=self.chooseImage)
        self.imageMenu.add_command(label="Descargar", command=self.saveMessage)
        self.menuBar.add_cascade(label="Imagen", menu=self.imageMenu)

        self.filterMenu = Menu(self.menuBar, tearoff=0)
        self.filterMenu.add_command(label="Recursiva en tono de gris", command = lambda: self.applyMosaic(1))
        self.filterMenu.add_command(label="Recursiva con color real", command = lambda: self.applyMosaic(2))

        self.menuBar.add_cascade(label="Filtros", menu=self.filterMenu)

        root.config(menu=self.menuBar)

    """
    Function to create two canvas
    Each canva contains an image
    """
    def createCanvas(self):
        #Place where the images will be shown
        self.originalWindow = Canvas(self, bg="white",width=500,height=500)
        self.originalWindow.pack(side=LEFT, fill=BOTH, expand=True)

        self.modifiedWindow = Canvas(self,bg ="white",width=500,height=500 )
        self.modifiedWindow.pack(side=RIGHT, fill=BOTH, expand=True)

    """
    Message to save the image
    """
    def saveMessage(self):
        if self.modifiedWindow.find_all() != ():
            self.top = Toplevel()

            self.label = Label (self.top, text= "Debes incluir el formato jpg o png al momento de escibir el nombre")
            self.label.pack()

            self.buttontext = StringVar()
            self.buttontext.set("Decargar")
            self.button = Button(self.top, textvariable=self.buttontext, command= self.saveImage).pack()
        else:
            tkMessageBox.showwarning("Elige una imagen")

    """
    Function to save an image
    """
    def saveImage(self):
        self.newImage.save(tkFileDialog.asksaveasfilename())
        self.top.destroy()

    """
    Function to choose an image
    """
    def chooseImage(self):
        path = tkFileDialog.askopenfilename()
        image = Imagen(path)
        self.original = image.getOriginal()
        self.modified = image.getModified()

        imageFile = ImageTk.PhotoImage(self.original)
        imageModified = ImageTk.PhotoImage(self.modified)

        self.originalWindow.image = imageFile
        self.originalWindow.create_image(imageFile.width()/2, imageFile.height()/2, anchor=CENTER, image=imageFile, tags="bg_img")

        self.modifiedWindow.image = imageModified
        self.modifiedWindow.create_image(imageModified.width()/2, imageModified.height()/2, anchor=CENTER, image=imageModified, tags="bg_img")


    """
    Window with the options to get the size of th mosaic
    """
    def applyMosaic(self,option):
        if self.modifiedWindow.find_all() != ():

            self.top = Toplevel()

            self.label = Label (self.top, text= "Introduce el tamaño del mosaico")
            self.label.pack()

            self.xValue = IntVar()
            Entry(self.top, textvariable=self.xValue).pack()

            self.yValue = IntVar()
            Entry(self.top, textvariable=self.yValue).pack()

            self.buttontext = StringVar()
            self.buttontext.set("Aplicar")
            self.button = Button(self.top, textvariable=self.buttontext, command= lambda: self.getMosaic(self.xValue,self.yValue,option)).pack()
        else:
            tkMessageBox.showwarning("Escoge una imagen antes de aplicar un filtro")

    """
    Applies the filter
    """
    def getMosaic(self,xValue,yValue,option):
        self.xValue = xValue.get()
        self.yValue = yValue.get()
        if(option == 1):
            generateGrayImages(self.original,self.modified)
            self.newImage = grayRecursive(self.original,self.modified,self.xValue,self.yValue)
            deleteGrayImages()
        elif(option == 2):
            self.newImage = colorRecursive(self.original,self.modified,self.xValue,self.yValue)
        resultingImage = ImageTk.PhotoImage(self.newImage)
        self.modifiedWindow.image = resultingImage
        self.modifiedWindow.create_image(resultingImage.width()/2, resultingImage.height()/2, anchor=CENTER, image=resultingImage, tags="bg_img")
        self.top.destroy()

"""
Function to center a window
"""
def center(toplevel):
    toplevel.update_idletasks()
    w = toplevel.winfo_screenwidth()
    h = toplevel.winfo_screenheight()
    size = tuple(int(_) for _ in toplevel.geometry().split('+')[0].split('x'))
    x = w/2 - size[0]/2
    y = h/2 - size[1]/2
    toplevel.geometry("%dx%d+%d+%d" % (size + (x, y)))


"""
Main function
"""
if __name__  == "__main__":
    root = Tk()
    root.title("Filtros Recursivos")
    app = Window(root)
    center(root)
    root.mainloop()
