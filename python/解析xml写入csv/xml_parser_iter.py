#coding=utf-8
from xml.etree import ElementTree as ET
import json
import sys
import codecs 
import csv


dataInfo_list = [] #存储报文retCode、mac、timestamp字段

def parseXml(fileName):
    per=ET.parse(fileName) #打开xml文件
    p=per.findall('./') #得到所有的标签
    for drug in p:#遍历每个responseData   queryString标签
        data_list = []
        #print('=====responseData=====')
        responseData = drug.find('responseData').text #得到responseData标签里的内容
        #print('responseData_content:'+responseData)
        rjson = json.loads(responseData)
        retCode = rjson['retCode']#获取retCode字段值
        #print('retCode:' + retCode )
        data_list.append(retCode)
        
        #print ('=====queryString=====')
        queryStr = drug.find('queryString').text #得到queryString标签里的内容
        #print ('queryString_content:'+queryStr)
        qjson = json.loads(queryStr)
        sn = qjson['sn']
        #print ('sn:' + sn)
        mac = sn[:12] #获取mac字段值
        timestamp = sn[12:] #获取timestamp字段值
        data_list.append(mac)
        data_list.append(timestamp)
        #print ('mac:' + mac + ',' + 'timestamp:' + timestamp)
        dataInfo_list.append(data_list)
    
def writeCsv(dataInfo_list,fileName):
    print ('The length of dataInfo_list is:' + str(len(dataInfo_list)) )
    #print (dataInfo_list)
    with open(fileName,"a+") as csvfile: # ab+去除空白行，又叫换行open(fileName,"ab+")
        #csvfile.write(codecs.BOM_UTF8)  #存入表内的文字格式 codecs.BOM_UTF8
        writer = csv.writer(csvfile)  #存入表时所使用的格式csv.writer(csvfile,dialect='excel')
        writer.writerow(['retCode','mac','timestamp'])#表头
        writer.writerows(dataInfo_list) #写入表
    
def writeTxt(dataInfo_list,fileName):
    print ('The length of dataInfo_list is:' + str(len(dataInfo_list)) )
    file = open(fileName,'a+')
    for i in range(len(dataInfo_list)):
        s = str(dataInfo_list[i]).replace('[','').replace(']','')#去除[],这两行按数据不同，可以选择
        s = s.replace("'",'').replace(',','') +'\n'   #去除单引号，逗号，每行末尾追加换行符
        file.write(s)
    file.close()
    print("保存文件成功") 
    
    
    
if __name__ == '__main__':
    if len(sys.argv) != 3:
        print ('Usage: python xml_parser_iter.py ./testlog01.html testlog01.csv')
        exit(1)
    parseXml(sys.argv[1])
    writeCsv(dataInfo_list,sys.argv[2])
    
