# !/usr/bin/python
# -*- coding: utf-8 -*-
"""
#python 3.x的写法：
import urllib.request
import json
def ip2city(ip):
    apiurl = "http://ip.taobao.com/service/getIpInfo.php?ip=%s" % ip
    content = urllib.request.urlopen(apiurl).read()
    data = json.loads(content)['data']
    code = json.loads(content)['code']
    if code == 0:
        return data["country"], data["region"],data["city"]
"""
 
 
import json
import urllib
import urllib2
 
 
class Location_Sina():
    """
    Interface of sina
    Under my test is of higher recognition and performance at home
    """
    def __init__(self, ip):
        self.ip = urllib.quote_plus(ip)
        self.api_url = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=%s' % self.ip
 
    def get_geoinfo(self):
        urlobj = urllib2.urlopen(self.api_url)
        data = urlobj.read()[20:][:-1]
        datadict = json.loads(data, encoding='utf-8')
        return datadict
 
    def get_country(self):
        datadict = self.get_geoinfo()
        return datadict['country']
 
    def get_province(self):
        datadict = self.get_geoinfo()
        return datadict['province']
 
    def get_city(self):
        datadict = self.get_geoinfo()
        return datadict['city']
 
 
class Location_Taobao():
    """
    Interface of taobao
    Under my test can be the 2ed choice
    """
    def __init__(self, ip):
        self.ip = urllib.quote_plus(ip)
        self.api_url = 'http://ip.taobao.com/service/getIpInfo.php?ip=%s' % self.ip
 
    def get_geoinfo(self):
        urlobj = urllib2.urlopen(self.api_url)
        data = urlobj.read()
        datadict = json.loads(data, encoding='utf-8')
        return datadict['data']
 
    def get_country(self):
        datadict = self.get_geoinfo()
        return datadict['country']
 
    def get_province(self):
        datadict = self.get_geoinfo()
        return datadict['region']
 
    def get_city(self):
        datadict = self.get_geoinfo()
        return datadict['city']
 
    def get_isp(self):
        datadict = self.get_geoinfo()
        return datadict['isp']
 
 
class Location_Freegeoip():
    """
    Interface of an excellent foreign service freegeoip
    The only shortage may be it return result in English
    """
    def __init__(self, ip):
        self.ip = urllib.quote_plus(ip)
        self.api_url = 'http://freegeoip.net/json/%s' % (self.ip)
 
    def get_geoinfo(self):
        urlobj = urllib2.urlopen(self.api_url)
        data = urlobj.read()
        datadict = json.loads(data, encoding='utf-8')
        return datadict
 
    def get_country(self):
        datadict = self.get_geoinfo()
        return datadict['country_name']
 
    def get_province(self):
        datadict = self.get_geoinfo()
        return datadict['region_name']
 
    def get_city(self):
        datadict = self.get_geoinfo()
        return datadict['city']
 
 
def ip2loc(ip):
    """
    An integrated user defined function via three APIs to transform IP to specific geographic position
    :param ip:IP
    :return:a list of ["country(or other)", "province(or other)", "city(or other)"]
    """
    try:
        LOC = Location_Sina(ip)
        loc = [LOC.get_country(), LOC.get_province(), LOC.get_city()]
        if all(loc): pass
        else:
            for i in range(len(loc)):
                if loc[i]: pass
                else:
                    LT_ = Location_Taobao(ip)
                    if   i == 0: loc[i] = LT_.get_country()
                    elif i == 1: loc[i] = LT_.get_province()
                    elif i == 2: loc[i] = LT_.get_city()
            if all(loc): pass
            else:
                for j in range(len(loc)):
                    if loc[j]: pass
                    else:
                        LF_ = Location_Freegeoip(ip)
                        if   j == 0: loc[j] = LF_.get_country()
                        elif j == 1: loc[j] = LF_.get_province()
                        elif j == 2: loc[j] = LF_.get_city()
                if all(loc): pass
                else:
                    for k in range(len(loc)):
                        if    loc[k]: pass
                        else: loc[k] = "暂未识别"
        return loc
    except:
        loc = ["无法处理", "无法处理", "无法处理"]
        return loc
 
 
if __name__ == '__main__':
    ip = '127.0.0.1'
 
    # Location_Sina
    # iploc = Location_Sina(ip)
    # print iploc.get_geoinfo()
    # print iploc.get_country()
    # print iploc.get_province()
    # print iploc.get_city()
 
    # Location_Taobao
    # iploc = Location_Taobao(ip)
    # print iploc.get_geoinfo()
    # print iploc.get_country()
    # print iploc.get_province()
    # print iploc.get_city()
    # print iploc.get_isp()
 
    # Location_Freegeoip
    # iploc = Location_Freegeoip(ip)
    # print iploc.get_geoinfo()
    # print iploc.get_country()
    # print iploc.get_province()
    # print iploc.get_city()
 
    # ip2loc
    loc = ip2loc(ip)
    print(loc)
    print(loc[0])
    print(loc[1])
    print(loc[2])
