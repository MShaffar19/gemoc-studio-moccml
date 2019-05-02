/*******************************************************************************
 * Copyright (c) 2017 I3S laboratory and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     I3S Laboratory - initial API and implementation
 *     ENSTA Bretagne - API update, feature enhancement and bug fix
 *******************************************************************************/
/*
 * generated by Xtext
 */
package org.eclipse.gemoc.moccml.constraint.ccslmocc.model.xtext;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class MoCDslUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return org.eclipse.gemoc.moccml.constraint.ccslmocc.model.xtext.ui.internal.MoCDslActivator.getInstance().getInjector("org.eclipse.gemoc.moccml.constraint.ccslmocc.model.xtext.MoCDsl");
	}

}