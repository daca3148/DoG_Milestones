CXX = g++
CXXFLAGS = -std=c++11 -Wall


all: git

git:
	git add .
	git commit -m "milestone2"
	git push
