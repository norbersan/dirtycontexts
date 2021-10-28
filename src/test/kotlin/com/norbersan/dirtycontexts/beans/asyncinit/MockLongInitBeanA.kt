package com.norbersan.dirtycontexts.beans.asyncinit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MockLongInitBeanA {
    @Autowired
    lateinit var beanContainer: AsyncInitBeanContainer

    @MockkBean
    lateinit var beanA: AsyncInitBeanA

    @Test
    fun mockBeanA(){
        every { beanA.toString() } answers { "mocked AsyncInitBeanA"}

        println()
        println("----------------------------")
        println("==> MOCKING AsyncInitBeanA")
        println("==> " + beanContainer.asyncInitBeanA.toString())
        println("==> " + beanContainer.asyncInitBeanB.toString())
        println("==> " + beanContainer.asyncInitBeanC.toString())
        println("----------------------------")
    }
}