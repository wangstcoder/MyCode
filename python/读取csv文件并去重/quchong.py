#coding=utf-8

import csv
import sys
import os

def readCsv(fileName,newFileName):
    with open(fileName,'rt') as csvfile:
        reader = csv.reader(csvfile)
        column = [row[0] for row in reader]
        #print(column)
        column = list(set(column))  #去重
        #print(column)
        writeCsv(newFileName,column)
    
def writeCsv(fileName,fields):
    
    out = open(fileName,'w', newline='')
    #设定写入模式
    csv_write = csv.writer(out,dialect='excel')
    #写入具体内容
    for field in fields:
        #print (field )
        column = []
        column.append(field)
        csv_write.writerow(column)
    #csv_write.writerow(csvrow1)
    #csv_write.writerow(csvrow2)
    print ("write over")
    
    
    
    
if __name__ == '__main__':
    
    if len(sys.argv) != 3:
        print ('Usage: python quchong.py .原始csv文件 去重后写入的文件')
        exit(1)
    
    #readCsv(sys.argv[1])
    readCsv(sys.argv[1],sys.argv[2])
    
