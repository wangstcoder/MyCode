# -*- coding:utf-8 -*-

import json

# result = json.loads(json_str)
# operationValue = result['operationValue']
#list1 = [{"name":"irPacketTotalFrames","value":"1"},{"name":"irTS5W1","value":"36470"},{"name":"irTS11W2","value":"61680"}]
#list2 = [{"name":"irTS5W1","value":"36470"},{"name":"irPacketTotalFrames","value":"1"},{"name":"irTS11W2","value":"61680"}]

def compar_list(list1,list2):
    temp1 = []
    for i in list1:
        if i not in list2:
            temp1.append(i)

    temp2 = []
    for i in list2:
        if i not in list1:
            temp2.append(i)

    print ('first: ' + str(temp1))
    print ('scend: ' + str(temp2))


def compare_json(json_str1, json_str2,key):
    list1 = json.loads(json_str1.encode('utf-8'))[key]
    list2 = json.loads(json_str2.encode('utf-8'))[key]

    len_a = len(list1)
    len_b = len(list2)

    if len_a != len_b:
        compar_list(list1,list2)
        return False
    else:
        sort1 = sorted(list1, key=lambda i: i['name'])
        sort2 = sorted(list2, key=lambda i: i['name'])
        if sort1 != sort2:
            compar_list(list1, list2)
            return False
        else:
            return (sort1 == sort2)



if __name__ == "__main__":

    json_str1 = '''{"deviceId":"04FA8313538C","operationName":"grSaveIrCode","operationValue":[{"name":"irPacketTotalFrames","value":"1"},{"name":"irPacketFrameFrameOrder","value":"1"},{"name":"irPacketFrameBytes","value":"44"},{"name":"irCmdType","value":"1"},{"name":"saveIrCodeDeviceId","value":"2"},{"name":"irTS1W1","value":"1280"},{"name":"irTS1W2","value":"46592"},{"name":"irTS2W1","value":"44"},{"name":"irTS2W2","value":"2048"},{"name":"irTS3W1","value":"0"},{"name":"irTS3W2","value":"0"},{"name":"irTS4W1","value":"0"},{"name":"irTS4W2","value":"127"},{"name":"irTS5W1","value":"36470"},{"name":"irTS5W2","value":"33914"},{"name":"irTS6W1","value":"31372"},{"name":"irTS6W2","value":"769"},{"name":"irTS7W1","value":"4382"},{"name":"irTS7W2","value":"3"},{"name":"irTS8W1","value":"4382"},{"name":"irTS8W2","value":"3"},{"name":"irTS9W1","value":"4382"},{"name":"irTS9W2","value":"61680"},{"name":"irTS10W1","value":"61680"},{"name":"irTS10W2","value":"3"},{"name":"irTS11W1","value":"4382"},{"name":"irTS11W2","value":"61680"}],"sn":"871540094480","callbackUrl":"http://smart.juidn.cn/uhomecallback"}
    '''
    json_str2 = '''{"deviceId":"04FA8313538C","operationName":"grSaveIrCode","operationValue":[{"name":"irPacketTotalFrames","value":"1"},{"name":"irPacketFrameFrameOrder","value":"1"},{"name":"irPacketFrameBytes","value":"44"},{"name":"irCmdType","value":"1"},{"name":"saveIrCodeDeviceId","value":"2"},{"name":"irTS1W1","value":"1280"},{"name":"irTS1W2","value":"46592"},{"name":"irTS2W1","value":"44"},{"name":"irTS2W2","value":"2048"},{"name":"irTS3W1","value":"0"},{"name":"irTS3W2","value":"0"},{"name":"irTS4W1","value":"0"},{"name":"irTS4W2","value":"127"},{"name":"irTS5W1","value":"36470"},{"name":"irTS5W2","value":"33914"},{"name":"irTS6W1","value":"31372"},{"name":"irTS6W2","value":"769"},{"name":"irTS7W1","value":"4382"},{"name":"irTS7W2","value":"3"},{"name":"irTS8W1","value":"4382"},{"name":"irTS8W2","value":"3"},{"name":"irTS9W1","value":"4382"},{"name":"irTS9W2","value":"61680"},{"name":"irTS10W1","value":"61680"},{"name":"irTS10W2","value":"3"},{"name":"irTS11W1","value":"4382"},{"name":"irTS11W2","value":"61680"}],"sn":"871540094480","callbackUrl":"http://smart.juidn.cn/uhomecallback"}
    '''
    key = 'operationValue'

    print (compare_json(json_str1, json_str2,key))




