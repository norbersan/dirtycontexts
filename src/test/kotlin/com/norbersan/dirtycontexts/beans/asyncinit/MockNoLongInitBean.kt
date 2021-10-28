package com.norbersan.dirtycontexts.beans.asyncinit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockNoLongInitBean {
    @Autowired
    lateinit var beanContainer: AsyncInitBeanContainer

    @Test
    fun mockNoBean(){

        println()
        println("----------------------------")
        println("==> NO MOCKING")
        println("==> " + beanContainer.asyncInitBeanA.toString())
        println("==> " + beanContainer.asyncInitBeanB.toString())
        println("==> " + beanContainer.asyncInitBeanC.toString())
        println("----------------------------")
    }
}