package com.x17.controller;

import com.github.pagehelper.PageInfo;
import com.x17.pojo.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring.xml", "classpath:config/springMVC.xml"})
public class EmployeeControllerTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    public EmployeeControllerTest() throws Exception {
    }

    @Before
    public void initMOckMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getEmps() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/").param("pn", "1")).andReturn();
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码："+pageInfo.getPageNum());
        System.out.println("总页码："+pageInfo.getPages());
        System.out.println("总计数："+pageInfo.getTotal());
        int[] nums = pageInfo.getNavigatepageNums();
        for (int i : nums) {
            System.out.println(" "+i);
        }
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("ID,"+employee.getEmpId()+"姓名"+employee.getEmpName()+employee.getDepartment().getDeptName());

        }

    }
}