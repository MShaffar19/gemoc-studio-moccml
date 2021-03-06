/**
 * Copyright (c) 2012-2017 GEMOC consortium.
 * 
 * http://www.gemoc.org
 * 
 * Contributors:
 *   Stephen Creff - ENSTA Bretagne [stephen.creff@ensta-bretagne.fr]
 *   
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * $Id$
 */
package org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.edit.provider;


import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.ClassicalExpressionFactory;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.provider.BooleanExpressionItemProvider;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.FSMModelFactory;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.edit.provider.FSMModelEditPlugin;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.EditionExtensionFactory;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.EditionExtensionPackage;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.IntSupEqual;

/**
 * This is the item provider adapter for a {@link org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.IntSupEqual} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntSupEqualItemProvider
	extends BooleanExpressionItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntSupEqualItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE);
			childrenFeatures.add(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns IntSupEqual.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IntSupEqual"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((IntSupEqual)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_IntSupEqual_type") :
			getString("_UI_IntSupEqual_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(IntSupEqual.class)) {
			case EditionExtensionPackage.INT_SUP_EQUAL__LEFT_VALUE:
			case EditionExtensionPackage.INT_SUP_EQUAL__RIGHT_VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfPlusAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfMinusAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfMultiplyAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfDivideAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 FSMModelFactory.eINSTANCE.createIntegerAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntegerRef()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createUnaryIntPlus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createUnaryIntMinus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntPlus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntMinus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntMultiply()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntDivide()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createSeqGetHead()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntegerVariableRef()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfPlusAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfMinusAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfMultiplyAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 EditionExtensionFactory.eINSTANCE.createIntSelfDivideAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 FSMModelFactory.eINSTANCE.createIntegerAssignement()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntegerRef()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createUnaryIntPlus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createUnaryIntMinus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntPlus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntMinus()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntMultiply()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntDivide()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createSeqGetHead()));

		newChildDescriptors.add
			(createChildParameter
				(EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE,
				 ClassicalExpressionFactory.eINSTANCE.createIntegerVariableRef()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == EditionExtensionPackage.Literals.INT_SUP_EQUAL__LEFT_VALUE ||
			childFeature == EditionExtensionPackage.Literals.INT_SUP_EQUAL__RIGHT_VALUE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return FSMModelEditPlugin.INSTANCE;
	}

}
