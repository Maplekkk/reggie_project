package com.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.pojo.AddressBook;
import com.maple.service.AddressBookService;
import com.maple.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author maple
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2023-01-23 12:25:18
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




