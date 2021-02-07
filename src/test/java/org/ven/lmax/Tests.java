package org.ven.lmax;

import org.junit.Assert;
import org.junit.Test;
import org.ven.lmax.producerconsumer.events.ValueEvent;

public class Tests {

    @Test
    public void testMutability(){
        ValueEvent ve = new ValueEvent();
        ve.setValue("1000");
        Assert.assertEquals("1000", ve.getValue() );

        //Value Event Mutability test
        ve.setValue("2000");
        Assert.assertEquals("2000", ve.getValue() );
    }


    @Test
    public void testSetter(){
        ValueEvent ve = new ValueEvent();
        ve.setValue("1000");
        Assert.assertEquals("1000", ve.getValue() );
    }
}
