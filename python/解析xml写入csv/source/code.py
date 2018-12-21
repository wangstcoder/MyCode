#coding=utf-8
from xml.etree import ElementTree as ET
 
f = open('Drug_info.txt','w')#保存路径
per=ET.parse('test2.xml')#打开xml文件
p=per.findall('./')#得到所有的drug标签
 
for drug in p:#遍历每个drug
    print('=====name=====')#这个后期可以删除，这里是方便你看输出
    name = drug.find('{http://www.drugbank.ca}name').text#得到drug标签下的name标签中的内容，但一定要加{http://www.drugbank.ca}，它这个XML文件就这么恶心，这个是最大的坑，今天遍历子节点才发现这个问题
    print('Drug_name:'+name)
    f.writelines(name)#写drugName
 
    print('=====targets_name======')
    for target in drug.iter('{http://www.drugbank.ca}target'):#遍历drug下的target标签中的name
        name = target.find('{http://www.drugbank.ca}name').text#使用find是找出第一个targetName就停止
        print('Targets_name:'+name)
        f.writelines(','+name)#写targetName
 
    print('=====SMILES=====')
    for CP in drug.iter('{http://www.drugbank.ca}calculated-properties'):#SMILES只存在于calculated-properties中，所以在calculated-properties中提取就可以了
        PROPERTY = CP.findall('{http://www.drugbank.ca}property')#再提calculated-properties标签中的property标签，因为kind，value都在property下
        index = []#建立两个空list，index是存储kind标签中的值，VALUE是存储value标签中的值，建立一一对应关系
        VALUE = []
        for ele in PROPERTY:
            kind = ele.find('{http://www.drugbank.ca}kind').text#得到kind的内容
            value = ele.find('{http://www.drugbank.ca}value').text#得到value的内容
            index.append(kind)#index中添加kind标签中的内容
            VALUE.append(value)#VALUE中添加value标签中的内容
        for idx in index:#遍历index列表
            if idx=='SMILES':#找到‘SMILES'所在index列表中的位置
                no = index.index(idx)#找到‘SMILES'所在index列表中的位置
                Molecular_formula = VALUE[no]#应为index和VALUE是一一对应关系，只要找到’SMILES‘在index中的位置也就知道分子式在VALUE中的位置
                print('分子式：'+Molecular_formula)
                f.writelines('#'+Molecular_formula)#写入分子式
                break#一个drug只有一个SMILES，所以找到SMILES直接中断，不必再循环下去，节省计算消耗
    f.writelines('\n')#每个药物获取所有信息结束后要加一个回车“\n”
f.close()#最后关闭文件
 
 
