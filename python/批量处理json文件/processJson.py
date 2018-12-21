#coding=utf-8 

import os
import json
from shutil import copyfile


#获取目标文件夹的路径
fileDir = 'E:\\SourceFile'
#不需要处理的文件的路径
noProDir = 'E:\\ProcessedFiles\\odd'
#文件处理后的路径
proDir = 'E:\\ProcessedFiles\\new'
#用来记录修改的数据
recordPath = 'E:\\ProcessedFiles\\record.txt'
#获取文件夹中的文件名称列表  
fileNames = os.listdir(fileDir)

#标记位，表示是否符合对比规则，默认对比一致
jsonFlag = True
#记录修改的值
#jsonRecord = ''
#testlist=[{"name":"currentTemperature","dataType":"int","readable":True,"caeType":1,"variants":{"minValue":10,"maxValue":99,"step":1,"k":1,"c":0}},{"name":"currentTemperature","dataType":"int","readable":True,"caeType":1,"variants":{"minValue":0,"maxValue":99,"step":1,"k":1,"c":0}}]



def writeFile_json(filePath,data):
    #打开文件并覆盖写入修改后内容
    with open(filePath, 'w+',encoding='utf-8') as f:
        #f.write( json.dump(data) )
        json_str = json.dump(data, f,ensure_ascii=False)


def writeFile_txt(filePath,data):
    with open(filePath, 'a+') as f:
        f.write( data)

def process_dict(dict,key):
    stepFlag = True
    minValueFlag = True
    variantsDict = dict
    print("The type of "+ key +" variantsDict is " + str(type(variantsDict)))
    step = variantsDict['step']
    minValue = variantsDict['minValue']
    maxValue = variantsDict['maxValue']
    k = variantsDict['k']
    c = variantsDict['c']
    record = ''
    if step == k:
        pass
    else:
        variantsDict['k'] = step
        stepFlag = False
        #print ("k和step不一致")

    if minValue == c:
        pass
    else:
        variantsDict['c'] = minValue
        minValueFlag = False
        #print("c和minValue不一致")

    #print ('process_dict方法里的stepFlag: ' + str(stepFlag))
    #print ('process_dict方法里的minValueFlag: ' + str(minValueFlag))
    #记录修改过的value

    if not (stepFlag and minValueFlag ):
        record = str(k)+ ',' + str(c) + ',' + str(step) + ',' + str(minValue) + ',' + str(maxValue)
        #print ('**dict**record*****  ' + record)

    return  variantsDict,(stepFlag and minValueFlag), record



def process_list(list,listName,typeId):
    print("The length of " + listName + " is " + str(len(list)))
    jsonFlag = True
    jsonRecord = ''
    for i in range(len(list)):
        caeType = list[i]["caeType"]
        name = list[i]["name"]
        #print (caeType)
        if (caeType == 1) or (caeType == 6):
            #print(caeType)
            global record
            list[i]["variants"],flag,record = process_dict( list[i]["variants"],listName)
            if flag:
                pass
            else:
                jsonFlag = False
                record = typeId + ',' + record + "," + name + '\n'
                jsonRecord += record
                #print('**list**record*****  ' + record)

    return list,jsonFlag,jsonRecord


def process_json(fileDir,fileNames):
    i = 1
    # 遍历文件名
    for filename in fileNames:
        global jsonFlag
        jsonFlag = True
        propertyList = []
        bigdataList = []
        filePath = fileDir + '\\' + filename
        print ('*********************fileNo: ' + str(i))
        print (filename)
        i += 1
        typeId = filename[:-5] #获取文件名typeid
        # 打开文件取出数据并修改，然后存入变量
        with open(filePath, 'r+', encoding='UTF-8') as f:
            data = json.load(f)
            #print(type(data))
            #print (data)
            if ('Property' in data.keys()):
                propertyList = data["Property"]
            if ('Bigdata' in data.keys()):
                bigdataList = data["Bigdata"]

            #判断 propertyList 和 bigdataList是否都为空 若都是则无需处理直接复制文件即可
            if  propertyList or bigdataList:
                data["Property"], propertyFlag ,record1 = process_list(propertyList, 'Property',typeId)
                data["Bigdata"], bigdataFlag ,record2 = process_list(bigdataList, 'Bigdata',typeId)
                #print ("propertyFlag is " + str(propertyFlag))
                #print("bigdataFlag is " + str(bigdataFlag))
                print("property record1 is: " + str(record1))
                print("bigdata record2 is: " + str(record2))
                #判断是否做过处理
                if (propertyFlag and bigdataFlag):
                    # 不需要处理的文件直接复制
                    copyfile(filePath, noProDir + '\\' + filename)
                else:
                    # 写入文件
                    filePath = proDir + '\\' + filename
                    writeFile_json(filePath, data)
                    #同时记录下来
                    if not propertyFlag:

                        #print ("property_record: " + record1)
                        writeFile_txt(recordPath,record1)
                    if not bigdataFlag:

                        #print("bigdata_record: " + record2)
                        writeFile_txt(recordPath, record2)



            else:
                # 不需要处理的文件直接复制
                copyfile(filePath,noProDir + '\\' +filename)



if __name__ == '__main__':
    process_json(fileDir,fileNames)










