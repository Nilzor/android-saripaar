package com.mobsandgeeks.saripaar;

import android.view.View;
import android.widget.TextView;
import java.util.List;

public class TextViewBoundValidator implements Validator.ValidationListener {
    private Validator mValidator;

    public TextViewBoundValidator(Validator validator) {
        mValidator = validator;
        mValidator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        resetAllErrorTexts();
    }

    private void resetAllErrorTexts() {
        List<Validator.ViewRulePair> fields = mValidator.getViewsAndRules();
        for (Validator.ViewRulePair pair : fields ) {
            if (pair.view instanceof TextView) {
                ((TextView) pair.view).setError(null);
            }
        }
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        if (failedView instanceof TextView) {
            TextView view = (TextView) failedView;
            view.requestFocus();
            view.setError(failedRule.getFailureMessage());
        }
    }
}