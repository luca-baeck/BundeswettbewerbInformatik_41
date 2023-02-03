import time

import numpy as np
from Keim import Keim
from datetime import datetime
from PIL import Image
import cv2

import keyboard

class Kristalmuster():
    global width
    global height

    global progressInfo
    global showImg

    global anzahlKeime
    global maxVelocity
    global showKeim
    global showKeimColor
    global keime

    global showRaster
    global rasterColor
    global rasterDist

    global data

    def __init__(self):
        self.width = 800
        self.height = 600

        self.progressInfo = True
        self.showImg = False

        self.anzahlKeime = 200
        self.maxVelocity = 100
        self.showKeim = True
        self.showKeimColor = [255, 0, 0]
        self.keime = []

        self.showRaster = False
        self.rasterColor = [122, 139, 139]
        self.rasterDist = 40

        self.data = np.zeros((self.height, self.width, 3), dtype=np.uint8)

        self.start_time = datetime.now()


    def drawRaster(self, rasterDist):
        x_pos = rasterDist
        while x_pos < self.width:
            for i in range(0, self.height):
                self.data[i, x_pos] = self.rasterColor
            x_pos = x_pos + rasterDist
        y_pos = rasterDist
        while y_pos < self.height:
            for i in range(0, self.width):
                self.data[y_pos, i] = self.rasterColor
            y_pos = y_pos + rasterDist

    def spawnKeime(self, anzahlKeime, showKeim):
        for i in range(0, anzahlKeime):
            neuerKeim = Keim(self.height, self.width, self.maxVelocity)
            check = True
            for keim in self.keime:
                check = not keim.compare(neuerKeim)
            if check:
                self.keime.append(neuerKeim)
                if showKeim:
                    self.data[neuerKeim.position[0], neuerKeim.position[1]] = self.showKeimColor
                else:
                    self.data[neuerKeim.position[0], neuerKeim.position[1]] = neuerKeim.orientationColor
            else:
                i = i-1

    def findMaxVelocity(self):
        maxVel = 0
        for keim in self.keime:
            if (max(keim.velocity) > maxVel):
                maxVel = max(keim.velocity)
        return maxVel


    def grow(self):
        if self.showImg:
            cv2.imshow('Kristallmuster', self.data)
            cv2.waitKey(1)
        maxVel = self.findMaxVelocity()
        while [0, 0, 0] in self.data:
            neueKristalle = []
            progress = int((np.count_nonzero(self.data) / (3 * self.width * self.height)) * 100)

            if(progress % 10 == 0):
                maxVel = self.findMaxVelocity()

            for keim in self.keime:
                if str(progress) == "99":
                    print("")
                    print("Anzahl Kristalle: " + str(len(self.keime)))
                    print("Kristall: " + str(self.keime.index(keim)))
                    print(str(keim.wachstum))
                    print(str(keim.velocity))
                    print("")
                    time.sleep(1)
                keim.set_time_intervall(1 / maxVel)
                data, kristalle = keim.grow(self.data)
                self.data = data
                for kristall in kristalle:
                    neueKristalle.append(kristall)
                if(keim.check[0] and keim.check[1] and keim.check[2] and keim.check[3]):
                    self.keime.remove(keim)

                if keyboard.is_pressed('q'):
                    return

            for kristall in neueKristalle:
                self.keime.append(kristall)

            if self.progressInfo:
                end_time = datetime.now()
                delta = end_time - self.start_time
                print(str(progress) + "% der Pixel sind berechnet. Das sind " + str(int(np.count_nonzero(self.data)/3)) + " Pixel von " + str(int(self.width*self.height)) + " Pixeln. Dafür benötigte Zeit: " + str(int(delta.total_seconds())) + "s")


            if self.showImg:
                cv2.imshow('Kristallmuster', self.data)
                cv2.waitKey(1)




    def main(self):
        self.spawnKeime(self.anzahlKeime, self.showKeim)
        self.grow()
        if self.showRaster:
            self.drawRaster(self.rasterDist)
        img = Image.fromarray(self.data, 'RGB')
        name = "Kristallmuster - " + str(self.anzahlKeime) + " Keime - MaxVelocity " + str(self.maxVelocity) + ".png"
        img.save(name)
        img.show()

        end_time = datetime.now()
        delta = end_time - self.start_time
        print("Fertig nach " + str(int(delta.total_seconds())) + "s")


kristalmuster = Kristalmuster()
kristalmuster.main()
