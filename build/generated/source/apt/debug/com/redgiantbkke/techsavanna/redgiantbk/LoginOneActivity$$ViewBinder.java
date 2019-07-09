// Generated code from Butter Knife. Do not modify!
package com.redgiantbkke.techsavanna.redgiantbk;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginOneActivity$$ViewBinder<T extends com.redgiantbkke.techsavanna.redgiantbk.LoginOneActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296421, "field '_emailText'");
    target._emailText = finder.castView(view, 2131296421, "field '_emailText'");
    view = finder.findRequiredView(source, 2131296420, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131296420, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131296307, "field '_loginButton'");
    target._loginButton = finder.castView(view, 2131296307, "field '_loginButton'");
  }

  @Override public void unbind(T target) {
    target._emailText = null;
    target._passwordText = null;
    target._loginButton = null;
  }
}
