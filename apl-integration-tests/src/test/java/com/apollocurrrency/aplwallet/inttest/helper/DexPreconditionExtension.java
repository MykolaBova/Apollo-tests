package com.apollocurrrency.aplwallet.inttest.helper;

import com.apollocurrrency.aplwallet.inttest.model.hooks.AccountsAPI;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DexPreconditionExtension implements BeforeAllCallback{
    @Override
    public void beforeAll(ExtensionContext context){
        System.out.println(context.getRequiredTestClass().getSimpleName());

    }


}
