import numpy as np

class Kristall:
    global position
    global orientationColor
    global velocity
    global rectangle

    global height
    global width

    global time_intervall

    def __init__(self, height, width, position, orientationColor, velocity, time_intervall):
        self.position = position
        self.orientationColor = orientationColor
        self.velocity = velocity
        #nach unten nach oben nach rechts nach links
        self.wachstum = [0, 0, 0, 0]

        self.time_intervall = time_intervall

        self.check = [False, False, False, False]

        self.width = width
        self.height = height

        if (self.position[0] == 0):
            self.velocity[1] = 0
            self.check[1] = True
        if (self.position[0] == height - 1):
            self.velocity[0] = 0
            self.check[0] = True

        if (self.position[1] == 0):
            self.velocity[3] = 0
            self.check[3] = True
        if (self.position[1] == width - 1):
            self.velocity[2] = 0
            self.check[2] = True

    def grow(self, data):
        kristalle = []
        self.wachstum[0] = self.wachstum[0] + (self.time_intervall*self.velocity[0])
        if self.wachstum[0] >= 1:
            self.wachstum[0] = self.wachstum[0] - 1
            position = [(self.position[0] + 1), self.position[1]]
            self.check[0] = True
            if not (np.any(data[position[0], position[1]])):
                kristall = Kristall(self.height, self.width, position, self.orientationColor, self.velocity, self.time_intervall)
                kristalle.append(kristall)
                data[position[0], position[1]] = self.orientationColor

        self.wachstum[1] = self.wachstum[1] + (self.time_intervall*self.velocity[1])
        if self.wachstum[1] <= -1:
            self.wachstum[1] = self.wachstum[1] + 1
            position = [(self.position[0] - 1), self.position[1]]
            self.check[1] = True
            if not (np.any(data[position[0], position[1]])):
                kristall = Kristall(self.height, self.width, position, self.orientationColor, self.velocity, self.time_intervall)
                kristalle.append(kristall)
                data[position[0], position[1]] = self.orientationColor


        self.wachstum[2] = self.wachstum[2] + (self.time_intervall*self.velocity[2])
        if self.wachstum[2] >= 1:
            self.wachstum[2] = self.wachstum[2] - 1
            position = [self.position[0], (self.position[1] + 1)]
            self.check[2] = True
            if not (np.any(data[position[0], position[1]])):
                kristall = Kristall(self.height, self.width, position, self.orientationColor, self.velocity, self.time_intervall)
                kristalle.append(kristall)
                data[position[0], position[1]] = self.orientationColor

        self.wachstum[3] = self.wachstum[3] + (self.time_intervall*self.velocity[3])
        if self.wachstum[3] <= -1:
            self.wachstum[3] = self.wachstum[3] + 1
            position = [self.position[0], (self.position[1] -1)]
            self.check[3] = True
            if not (np.any(data[position[0], position[1]])):
                kristall = Kristall(self.height, self.width, position, self.orientationColor, self.velocity, self.time_intervall)
                kristalle.append(kristall)
                data[position[0], position[1]] = self.orientationColor
        return data, kristalle

    def set_time_intervall(self, time_intervall):
        self.time_intervall = time_intervall


