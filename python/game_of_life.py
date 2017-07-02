#!/usr/bin/env python

import sys, os, time
from random import randint

GRID_WIDTH = 190 #cells
GRID_HEIGHT = 65 #cells
DELAY = 0 #seconds 

class Point(object):
	def __init__(self,x,y):
		self.x = x
		self.y = y
			
	def __str__(self):
		return "(" + str(self.x) + "," + str(self.y) + ")"

class Cell(object):
	def __init__(self,point,alive):
		self.point = point
		self.alive = alive 
		
	def set(self,alive):
		self.alive = alive
	
	def __str__(self):
		return "X" if self.alive else " "
		
	@staticmethod
	def count_alive(cells):
		living = 0
		for cell in cells:
			if cell.alive:
				living += 1
		return living		

class Grid(object):
	def __init__(self):
		self.cells = [[Cell(Point(x,y),bool(randint(0, 1))) for x in range(0,GRID_WIDTH)] for y in range(0,GRID_HEIGHT)]
	
	def __str__(self):
		grid = ""
		for row in self.cells:
			for cell in row:
				grid += str(cell)
			grid += "\n"
		return grid
		
	def get_neighbors(self,point):
		neighbors = []
		if point.x > 0:
			neighbors.append(self.cells[point.y][point.x - 1])
			if point.y > 0:
				neighbors.append(self.cells[point.y - 1][point.x - 1])
			if point.y < GRID_HEIGHT - 1: 
				neighbors.append(self.cells[point.y + 1][point.x - 1])
		if point.x < GRID_WIDTH - 1:
			neighbors.append(self.cells[point.y][point.x + 1])
			if point.y > 0: 
				neighbors.append(self.cells[point.y - 1][point.x + 1])
			if point.y < GRID_HEIGHT - 1: 
				neighbors.append(self.cells[point.y + 1][point.x + 1])
		if point.y > 0:
			neighbors.append(self.cells[point.y - 1][point.x])
		if point.y < GRID_HEIGHT - 1:
			neighbors.append(self.cells[point.y + 1][point.x])
		return neighbors		

	def update(self):
		new_cells = [[Cell(Point(x,y),False) for x in range(0,GRID_WIDTH)] for y in range(0,GRID_HEIGHT)]
		for row in self.cells:
			for cell in row:
				living_neighbors = Cell.count_alive(self.get_neighbors(cell.point))
				if cell.alive:
					new_cells[cell.point.y][cell.point.x].set(living_neighbors == 2 or living_neighbors == 3)
				else:
					new_cells[cell.point.y][cell.point.x].set(living_neighbors == 3)
		self.cells = new_cells
					
#main
grid = Grid();
while True:
	print(grid)
	grid.update()
	time.sleep(DELAY)
	os.system('clear')
