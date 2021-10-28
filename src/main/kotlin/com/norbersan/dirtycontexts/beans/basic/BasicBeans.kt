package com.norbersan.dirtycontexts.beans.basic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

abstract class AbstractBean(){
    override fun toString() = "==> bean ${javaClass.simpleName}"
    init {
        println("==> ${javaClass.simpleName} init in thread ${Thread.currentThread()}")
    }

    @PostConstruct
    fun setup(){
        println("==> ${javaClass.simpleName} @Postconstruct in thread ${Thread.currentThread()}")
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
    }

    @PreDestroy
    fun teardown(){
        println("==> ${javaClass.simpleName} @Predestroy in thread ${Thread.currentThread()}")
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}

@Component
class BeanA() : AbstractBean(){
    init {
        println("==> Created bean of type ${javaClass.simpleName}")
    }
}

@Component
class BeanB() : AbstractBean(){
    init {
        println("==> Created bean of type ${javaClass.simpleName}")
    }
}

@Component
class BeanC() : AbstractBean(){
    init {
        println("==> Created bean of type ${javaClass.simpleName}")
    }
}

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class BeanContainer(){
    @Autowired
    lateinit var beanA: BeanA

    @Autowired
    lateinit var beanB: BeanB

    @Autowired
    lateinit var beanC: BeanC

    init {
        println("==> Created bean of type ${javaClass.simpleName}")
    }

    @PostConstruct
    fun setup(){
        println("==> @PostConstruct method called for bean of type ${javaClass.simpleName}")
    }

    @PreDestroy
    fun teardown(){
        println("==> @PreDestroy method called for bean of type ${javaClass.simpleName}")
    }
}