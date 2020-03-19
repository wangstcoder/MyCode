def analyze_json(jsons):
    """
    解析传进来的jsons,将jsons解析成key-value并输出
    :param jsons: 需要解析的json字符串
    :return:
    """
    key_value = ''
    # isinstance函数是Python的内部函数，他的作用是判断jsons这个参数是否为dict类型
    # 如果是的话返回True，否则返回False
    if isinstance(jsons, dict):
        for key in jsons.keys():
            key_value = jsons.get(key)
            if isinstance(key_value, dict):
                analyze_json(key_value)
            elif isinstance(key_value, list):
                for json_array in key_value:
                    analyze_json(json_array)
            else:
                print(str(key) + " = " + str(key_value))
    elif isinstance(jsons, list):
        for json_array in jsons:
            analyze_json(json_array)


def output_value(jsons, key):
    """
    通过参数key，在jsons中进行匹配并输出该key对应的value
    :param jsons: 需要解析的json串
    :param key: 需要查找的key
    :return:
    """
    key_value = ''
    if isinstance(jsons, dict):
        for json_result in jsons.values():
            if key in jsons.keys():
                key_value = jsons.get(key)
            else:
                output_value(json_result, key)
    elif isinstance(jsons, list):
        for json_array in jsons:
            output_value(json_array, key)
    if key_value != '':
        print(str(key) + " = " + str(key_value))

