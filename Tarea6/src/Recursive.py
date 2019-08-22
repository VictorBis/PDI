import os
import math
from PIL import ImageTk, Image
import PIL.Image

"""
Function to apply the gray filter to an image
"""
def grayFilter(image,modified):
    rgb = image.convert('RGB')
    pixels = modified.load()
    for i in range(image.size[0]):
        for j in range(image.size[1]):
            r,g,b = rgb.getpixel((i,j))
            gray = int(round((r+g+b)/3))
            pixels[i,j] = (gray,gray,gray)
    return modified

"""
Function to apply the Brillo filter to an image
"""
def brillo(image,modified,light):
    rgb = image.convert('RGB')
    pixels = modified.load()
    brillo = light
    for i in range(image.size[0]):
        for j in range(image.size[1]):
            r,g,b = rgb.getpixel((i,j))
            r = r+brillo
            g = g+brillo
            b = b+brillo
            r = min(max(r,0),255)
            g = min(max(g,0),255)
            b = min(max(b,0),255)
            pixels[i,j] = (r,g,b)
    return modified

"""
Function to apply the rgb fliter to an image
"""
def rgbFilter(image,modified,red,green,blue):
    rgb = image.convert('RGB')
    pixels = modified.load()
    for i in range(image.size[0]):
        for j in range(image.size[1]):
            r,g,b = rgb.getpixel((i,j))
            andR = (red & r)
            andG = (green & g)
            andB = (blue & b)
            pixels[i,j] = (andR,andG,andB)
    return modified

"""
Function to generate different gray images
"""
def generateGrayImages(image,modified):
    gray = grayFilter(image,modified)
    light = -128
    count = 1
    while(light < 129):
        im = brillo(gray,image,light)
        im.save("imagen"+str(count)+".png","PNG")
        light = light + 9
        count = count + 1

"""
Function to delete the generated images
"""
def deleteGrayImages():
    for i in range(1,30):
        os.remove("imagen"+str(i)+".png")

"""
Function to apply the Recusrivas en tonos de gris filter to an image
"""
def grayRecursive(image,modified,mosX,mosY):
    gray = grayFilter(image,modified)
    size = mosX,mosY
    posX = 0
    posY = 0
    finishX = 0
    finishY = 0
    grayAvg = 0
    avg = 0
    width = gray.size[0]
    height = gray.size[1]
    rgb = gray.convert('RGB')
    pixels = modified.load()
    for i in range(0,width,mosX):
        finishX = i + mosX
        for j in range(0,height,mosY):
            finishY = j + mosY
            for k in range(i,finishX):
                if (k >= width):
                    break
                for l in range(j,finishY):
                    if (l >= height):
                        break
                    r,g,b = rgb.getpixel((k,l))
                    grayAvg += r
                    avg += 1
            avgRec = (grayAvg/avg)
            grayAvg = 0
            avg = 0
            if(avgRec >= 0 and avgRec < 9):
                img = PIL.Image.open('imagen1.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 9 and avgRec < 18):
                img = PIL.Image.open('imagen2.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 18 and avgRec < 27):
                img = PIL.Image.open('imagen3.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 27 and avgRec < 36):
                img = PIL.Image.open('imagen4.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 36 and avgRec < 45):
                img = PIL.Image.open('imagen5.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 45 and avgRec < 54):
                img = PIL.Image.open('imagen6.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 54 and avgRec < 63):
                img = PIL.Image.open('imagen7.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 63 and avgRec < 72):
                img = PIL.Image.open('imagen8.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 72 and avgRec < 81):
                img = PIL.Image.open('imagen9.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 81 and avgRec < 90):
                img = PIL.Image.open('imagen10.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 90 and avgRec < 99):
                img = PIL.Image.open('imagen11.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 99 and avgRec < 108):
                img = PIL.Image.open('imagen12.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 108 and avgRec < 117):
                img = PIL.Image.open('imagen13.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 117 and avgRec < 126):
                img = PIL.Image.open('imagen14.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 126 and avgRec < 135):
                img = PIL.Image.open('imagen15.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 135 and avgRec < 144):
                img = PIL.Image.open('imagen16.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 144 and avgRec < 153):
                img = PIL.Image.open('imagen17.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 153 and avgRec < 162):
                img = PIL.Image.open('imagen18.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 162 and avgRec < 171):
                img = PIL.Image.open('imagen19.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 171 and avgRec < 180):
                img = PIL.Image.open('imagen20.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 180 and avgRec < 189):
                img = PIL.Image.open('imagen21.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 189 and avgRec < 198):
                img = PIL.Image.open('imagen22.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 198 and avgRec < 207):
                img = PIL.Image.open('imagen23.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 207 and avgRec < 216):
                img = PIL.Image.open('imagen24.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 216 and avgRec < 225):
                img = PIL.Image.open('imagen25.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 225 and avgRec < 234):
                img = PIL.Image.open('imagen26.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 234 and avgRec < 243):
                img = PIL.Image.open('imagen27.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 243 and avgRec < 252):
                img = PIL.Image.open('imagen28.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            elif(avgRec >= 252 and avgRec < 256):
                img = PIL.Image.open('imagen29.png')
                img = img.resize(size)
                modified.paste(img,(posX,posY))
            posY += mosY
        posX += mosX
        posY = 0
    return modified

"""
Function to apply the Recursivas a color filter to an image
"""
def colorRecursive(image,modified,mosX,mosY):
    size = mosX,mosY
    posX = 0
    posY = 0
    finishX = 0
    finishY = 0
    redAvg = 0
    greenAvg = 0
    blueAvg = 0
    avg = 0
    width = image.size[0]
    height = image.size[1]
    rgb = image.convert('RGB')
    pixels = modified.load()
    for i in range(0,width,mosX):
        finishX = i + mosX
        for j in range(0,height,mosY):
            finishY = j + mosY
            for k in range(i,finishX):
                if (k >= width):
                    break
                for l in range(j,finishY):
                    if (l >= height):
                        break
                    r,g,b = rgb.getpixel((k,l))
                    redAvg += r
                    greenAvg += g
                    blueAvg += b
                    avg += 1
            rAvg = (redAvg/avg)
            gAvg = (greenAvg/avg)
            bAvg = (blueAvg/avg)
            redAvg = 0
            greenAvg = 0
            blueAvg = 0
            avg = 0
            result = PIL.Image.new('RGB',(500,500))
            img = rgbFilter(image,result,rAvg,gAvg,bAvg) #original image with the rgb filter applied
            img = img.resize(size)
            modified.paste(img,(posX,posY)) #place the modified image in the resulting one
            posY += mosY
        posX += mosX
        posY = 0
    return modified
