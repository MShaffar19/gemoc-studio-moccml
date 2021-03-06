/**
 * Copyright (c) 2012-2017 GEMOC consortium.
 * 
 * http://www.gemoc.org
 * 
 * Contributors:
 *   Stephen Creff - ENSTA Bretagne [stephen.creff@ensta-bretagne.fr]
 *   Papa Issa Diallo - ENSTA Bretagne [papa_issa.diallo@ensta-bretagne.fr]
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * $Id$
 */
package org.eclipse.gemoc.moccml.constraint.ccslmocc.model.design.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.eclipse.gemoc.moccml.constraint.ccslmoc.model.moccml.moccml.StateMachineRelationDefinition;
import org.eclipse.gemoc.moccml.constraint.ccslmoc.model.moccml.moccml.StateRelationBasedLibrary;
import org.eclipse.gemoc.moccml.constraint.ccslmocc.model.xtext.MoCDslRuntimeModule;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.AbstractAction;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.Guard;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.IntegerAssignement;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.State;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.Transition;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.Trigger;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.IntInfEqual;
import org.eclipse.gemoc.moccml.constraint.fsmkernel.model.FSMModel.editionextension.IntSupEqual;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.ImportStatement;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.NamedElement;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.BasicType.IntegerElement;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.BasicType.Type;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.And;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.BinaryIntegerExpression;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.BooleanExpression;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.ClassicalExpression;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntDivide;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntEqual;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntInf;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntMinus;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntMultiply;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntPlus;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntSup;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntegerExpression;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntegerRef;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.IntegerVariableRef;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.Not;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.Or;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClassicalExpression.Xor;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.AbstractEntity;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.BindableEntity;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.ConcreteEntity;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.RelationDeclaration;
import fr.inria.aoste.timesquare.ccslkernel.model.TimeModel.CCSLModel.ClockExpressionAndRelation.RelationLibrary;



/**
 * MoCMLServices for CCSLMoCC odesign
 * @author Stfun
 *
 */
public class MoCMLServices {
	
	public MoCMLServices() {}
	
	/**
	 * Get an ordered list of states and transitions for a given state machine, starting
	 * from the initial state, its outgoing transitions, then the other states and outgoing transitions.
	 * @param fsm : the given state machine
	 * @return the ordered list of states and transitions
	 */
	
	
	public int numRelationDeclaration (StateRelationBasedLibrary stateLib)
	{
		int num = 0;
		EList<RelationLibrary> lst = new BasicEList<RelationLibrary>();
		lst =  stateLib.getRelationLibraries();
		for(RelationLibrary rl : lst)
		{
			num = num + rl.getRelationDeclarations().size();
		}
		return num;
	}
	
	public int numStateMachineRelationDefinition (StateRelationBasedLibrary stateLib)
	{
		int num = 0;
		EList<RelationLibrary> lst = new BasicEList<RelationLibrary>();
		lst =  stateLib.getRelationLibraries();
		for(RelationLibrary rl : lst)
		{
			num = num + rl.getRelationDefinitions().size();
		}
		return num;
	}
	
	public int numFormalParam (StateRelationBasedLibrary stateLib)
	{
		int num = 0;
		EList<RelationLibrary> lst = new BasicEList<RelationLibrary>();
		EList<RelationDeclaration> lstDecl = new BasicEList<RelationDeclaration>();
		lst =  stateLib.getRelationLibraries();
		for(RelationLibrary rl : lst)
		{
			lstDecl.addAll(rl.getRelationDeclarations());
		}
		
		for(RelationDeclaration rd : lstDecl)
		{
			num = num + rd.getParameters().size();
		}
		
		return num;
	}
	
	public int numStates (StateRelationBasedLibrary stateLib)
	{
		int num = 0;
		EList<RelationLibrary> lst = new BasicEList<RelationLibrary>();
		EList<StateMachineRelationDefinition> lstDef = new BasicEList<StateMachineRelationDefinition>();
		lst =  stateLib.getRelationLibraries();
		for(RelationLibrary rl : lst)
		{
			lstDef.addAll((Collection<? extends StateMachineRelationDefinition>) rl.getRelationDefinitions());
		}
		
		for(StateMachineRelationDefinition rd : lstDef)
		{
			num = num + rd.getStates().size();
		}
		
		return num;
	}
	
	
	public Collection<NamedElement> getDetails(StateMachineRelationDefinition fsm){
		Collection<NamedElement> lst = new ArrayList<NamedElement>();
		//initial state
		lst.add(fsm.getInitialStates().get(0));
		//outgoing transitions
		if ((fsm.getInitialStates().get(0).getOutputTransitions()!=null) &&(!fsm.getInitialStates().get(0).getOutputTransitions().isEmpty())) {
			lst.addAll(fsm.getInitialStates().get(0).getOutputTransitions());
		}
		//do the same for the other states..
		for (State state : fsm.getStates()) {
			if (!fsm.getInitialStates().contains(state)) {
				lst.add(state);
				//outgoing transitions
				if ((state.getOutputTransitions()!=null) &&(!state.getOutputTransitions().isEmpty())) {
					lst.addAll(state.getOutputTransitions());
				}
			}
		}
		return lst;
	}
	
	/**
	 * Return true if the current state relation based library already import a library with a particular uri.
	 * @param stateLib
	 * @return all existing types referenced by the model
	 */
	public Boolean containsImportedLibrary(StateRelationBasedLibrary stateLib, String uri){
		for(ImportStatement importedStatement: stateLib.getImports()){
			if(importedStatement.getImportURI().equals(uri)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get all types, including the given entry point type.
	 * @param type
	 * @return all existing types referenced by the model
	 */
	public Collection<Type> getAllExistingTypes(Type type){
		if (type.eContainer() instanceof StateRelationBasedLibrary) {
			return getAllExistingTypes((StateRelationBasedLibrary)type.eContainer());
		}
		return null;
	}
	
	/**
	 * Get all transition elements(guards, event or actions)
	 */
	public Collection<EObject> getTransitionElements(Transition transition){
		Collection<EObject> lst = new ArrayList<EObject>();
		if(transition.getGuard()!=null){
			lst.add(transition.getGuard());
		}
		if(transition.getTrigger()!=null){
			lst.add(transition.getTrigger());
		}
		if(transition.getActions()!=null && !transition.getActions().isEmpty()){
			lst.addAll(transition.getActions());
		}
		return lst;
	}
	
	/**
	 * Get all types, contained by the library or imported from others.
	 * @param stateLib
	 * @return all existing types referenced by the model
	 */
	public Collection<Type> getAllExistingTypes(StateRelationBasedLibrary stateLib){
		Collection<Type> lst = new ArrayList<Type>();
		if ((stateLib.getImports()!=null) && (!stateLib.getImports().isEmpty())) {
			lst.addAll(getImportedTypes(stateLib));
		}
		if ((stateLib.getPredefinedTypes()!=null)&&(!stateLib.getPredefinedTypes().isEmpty())) {
			lst.addAll(stateLib.getPredefinedTypes());
		}		
		return lst;
	}
	
	
	/**
	 * Get all types from imported libraries.
	 * @param stateLib
	 * @return all existing imported types
	 */
	public Collection<Type> getImportedTypes(StateRelationBasedLibrary stateLib){
		Collection<Type> lst = new ArrayList<Type>();
		if ((stateLib.getImports()!=null) && (!stateLib.getImports().isEmpty())) {
			for (Iterator<Notifier> iterator = stateLib.eResource().getResourceSet().getAllContents(); iterator.hasNext();) {
				Notifier n = iterator.next();
				if (n instanceof Type) {
					if (((Type) n).eContainer()!=stateLib) {
						lst.add((Type)n);
					}
				}
			}
		}
		return lst;
	}
	
	/**
	 * Are considered the bindable entities contained in the fsm, and
	 * the ones referenced in the associated declaration
	 * @param fsm
	 * @return
	 */
	public Collection<BindableEntity> getExistingBindableEntities(StateMachineRelationDefinition fsm){	
		Collection<BindableEntity> lst = new ArrayList<BindableEntity>();
		if ((fsm.getDeclaration()!=null)&&(!fsm.getDeclaration().getParameters().isEmpty())) {
			lst.addAll(fsm.getDeclaration().getParameters());
		}
		if (fsm.getDeclarationBlock()!=null) {
			for (Iterator<EObject> iterator = fsm.getDeclarationBlock().eAllContents(); iterator.hasNext();) {
				EObject eo = iterator.next();
				if (eo instanceof BindableEntity) {
					lst.add((BindableEntity)eo);
				}
			}
		}
		return lst;
	}
	
	/**
	 * Compute the given AbstractEntity label
	 * @param element : the AbstractEntity
	 * @return the computed label "[name]: [type]"
	 */
	public String computeLabel(AbstractEntity element){
		StringBuilder sb = new StringBuilder(16);
		/*if (element.getDesiredEventKind()!=null) {
			sb.append("{");
			sb.append(element.getDesiredEventKind().getLiteral());
			sb.append("} ");
		}*/
		sb.append(element.getName());
		if (element.getType()!=null) {
			sb.append(": ");
			sb.append(element.getType().getName());
		}
		return sb.toString();
	}
	
	/**
	 * Create the given State label
	 * @param element : the State
	 * @return the created label "[FSM-name-initials]_S[NumberOfStates]"
	 */
	public String createLabel(State element){
		StringBuilder sb = new StringBuilder(16);
		sb.append(getUpperCaseLettersAndNumbers(((StateMachineRelationDefinition)element.eContainer()).getName()));
		sb.append("_S");
		sb.append(((StateMachineRelationDefinition)element.eContainer()).getStates().size());
		return sb.toString();
	}
	
	
	/**
	 * Filter (keep) the Upper case letter and numbers from a string
	 * @param name : the string
	 * @return "AzerTy1" -> "AT1"
	 */
	private String getUpperCaseLettersAndNumbers(String name){
		if ((name!=null)&&(!name.isEmpty())) {
			StringBuilder sb = new StringBuilder(16);
			for(int i=0; i<name.length(); i++) {
		        if(Character.isUpperCase(name.charAt(i))) {
		        	sb.append(name.charAt(i));
		        }else if (Character.isDigit(name.charAt(i))) {
		        	sb.append(name.charAt(i));
				}
			}
			if (sb.length()==0) {
				sb.append(name.charAt(0));
			}
			return sb.toString();
		}
		return "";
	}
	
	/**
	 * Compute a detailed label for a transition
	 * @param element : the given transition
	 * @return "	to:[targetName], defined as:[transitionLabel]"
	 */
	public String computeDetailedLabel(Transition element){
		StringBuilder sb = new StringBuilder(16);
		sb.append("    to:").append(element.getTarget().getName());
		sb.append(", defined as:").append(computeLabel(element));
		return sb.toString();
	}
	
	
	/**
	 * Compute a transition label, included guard, trigger and transition whether existing..
	 * @param element : the given transition
	 * @return
	 */
	public String computeLabel(Transition element){
		StringBuilder sb = new StringBuilder(16);
		Injector injector = Guice.createInjector(new MoCDslRuntimeModule());
		Serializer serializer = injector.getInstance(Serializer.class);
		boolean changed = false;
		
		if((element.getTrigger()==null) && (element.getGuard()==null) && (element.getActions().isEmpty()))
		{
			sb.append("{Empty Transition}");
		}
		if (element.getTrigger()!=null) {
			sb.append("when {" +serializer.serialize(element.getTrigger())+"}\n");
//			if (!((Trigger)element.getTrigger()).getTrueTriggers().isEmpty()) {
//				
//				for (Iterator<BindableEntity> iterator = ((Trigger)element.getTrigger()).getTrueTriggers().iterator(); iterator
//						.hasNext();) {
//					BindableEntity trigger = (BindableEntity) iterator.next();
//					sb.append(trigger.getName());
//					if (iterator.hasNext()) {
//						sb.append(", ");
//					}
//				}			
//			}else if (!((Trigger)element.getTrigger()).getFalseTriggers().isEmpty()) {
//				for (Iterator<BindableEntity> iterator = ((Trigger)element.getTrigger()).getFalseTriggers().iterator(); iterator
//						.hasNext();) {
//					BindableEntity trigger = (BindableEntity) iterator.next();
//					sb.append(trigger.getName());
//					if (iterator.hasNext()) {
//						sb.append(", ");
//					}
//				}
//			}
//			changed = true;
		}
		if (element.getGuard()!=null) {
			sb.append("if "+serializer.serialize(element.getGuard()));
//		
//			sb.append(" [");
//			String s = serializer.serialize(((Guard)element.getGuard()).getValue());
//			sb.append(s);
//			sb.append("] ");
//			changed = true;
		} 
		if (!element.getActions().isEmpty()) {
				sb.append("\n / ");
				for (Iterator<AbstractAction> iterator = element.getActions().iterator(); iterator
						.hasNext();) {
					AbstractAction action = iterator.next();
					sb.append("\n"+serializer.serialize(action));
//					sb.append("do ");
//					switch (action.eClass().getClassifierID()) {
//					case CcslmoccPackage.START_CLOCK:
//						sb.append("start ");
//						sb.append(((StartClock)action).getClock().getName());
//						break;
//					case CcslmoccPackage.FINISH_CLOCK:
//						sb.append("finish ");
//						sb.append(((FinishClock)action).getClock().getName());
//						break;
//					default: //FSMModelPackage.INTEGER_ASSIGNEMENT or block
//						if (action instanceof IntegerAssignement) {
//							sb.append("assign ");
//							sb.append(((IntegerAssignement)action).getName());
//						}else {
//							sb.append("assign ");
//							sb.append(((IntegerAssignementBlock)action).getName());
//						}
//						
//						break;
//					}

				}
		}

		return sb.toString();
	}
		
	public String newComputeLabel(Transition element)
	{
			StringBuilder sb = new StringBuilder(16);
			boolean changed = false;

			if (element.getTrigger()!=null) 
			{
				if (!((Trigger)element.getTrigger()).getTrueTriggers().isEmpty()) 
				{
					sb.append('{');
					for (Iterator<BindableEntity> iterator = ((Trigger)element.getTrigger()).getTrueTriggers().iterator(); iterator
							.hasNext();) {
						BindableEntity trigger = (BindableEntity) iterator.next();
						sb.append(trigger.getName());
						if (iterator.hasNext()) {
							sb.append(", ");
						}
					}			
				sb.append('}' +"\n");
				}

				else if (!((Trigger)element.getTrigger()).getFalseTriggers().isEmpty()) 
				{
					sb.append('{');
					for (Iterator<BindableEntity> iterator = ((Trigger)element.getTrigger()).getFalseTriggers().iterator(); iterator
							.hasNext();) {
						BindableEntity trigger = (BindableEntity) iterator.next();
						sb.append('!'+ trigger.getName());
						if (iterator.hasNext()) {
							sb.append(", ");
						}
					}
					sb.append('}'+"\n");
				}
				changed = true;
			}

			if (element.getGuard()!=null) {
				//System.out.println("===========================================");
				//System.out.println(element.getGuard().toString());
				//System.out.println(element.getGuard().getClass().getSimpleName());
				//System.out.println("===========================================");
				
				sb.append("[");
				if(((Guard)element.getGuard()).getValue()!=null)
					{
						BooleanExpression expr = ((Guard)element.getGuard()).getValue();
						String gVal = toStringGuard(expr);
						sb.append(gVal);
					}				sb.append("]"+"\n");
				changed = true;
			} 

			if (!element.getActions().isEmpty()) {
					sb.append("/");
					String expr = toStringAction(element);
					sb.append(expr);
					changed = true;
			}

			if (!changed) {
				sb.append("Empty");
			}
			return sb.toString();
	}

	public String toStringAction(Transition t){
			
			StringBuilder st= new StringBuilder();
			List<AbstractAction> act = t.getActions();
			//List<String> intref = new ArrayList<String>(); // List of IntegerRef IntegerVariableRef and names
			//List<EObject> allObj = new ArrayList<EObject>();
			for(int i=0; i< act.size();i++)
			{
				String s = "";
				if (act.get(i) instanceof IntegerAssignement)
				{
					IntegerAssignement imp;
					imp = (IntegerAssignement) act.get(i);
					if ((imp.getRightValue() instanceof IntegerRef) || (imp.getRightValue() instanceof IntegerVariableRef))
					{
						IntegerRef ti;
						ti = (IntegerRef) imp.getLeftValue();
						s = ti.getIntegerElem().getName() + "="+sepVar(imp.getRightValue());
					}
					else if (!((imp.getRightValue() instanceof IntegerRef) || (imp.getRightValue() instanceof IntegerVariableRef)))
					{
						if(imp.getRightValue() instanceof BinaryIntegerExpression)
						{
							IntegerRef ti;
							ti = (IntegerRef) imp.getLeftValue();
							BinaryIntegerExpression tr;
							tr = (BinaryIntegerExpression) imp.getRightValue();
							s = ti.getIntegerElem().getName() + "="+buildActionBlocUnit(tr);
						}
					}
				}
				st.append(s + "\n");
			}

			return st.toString();
	}
	
	public String filterIntegerNames(String integarName)
	{
		String s = "";
		
		if(integarName.equals("zero")==true)
		s= "0";
		else if(integarName.equals("one")==true)
		s= "1";
		else if(integarName.equals("two")==true)
		s= "2";
		else if(integarName.equals("three")==true)
		s= "3";
		else if(integarName.equals("four")==true)
		s= "4";
		else if(integarName.equals("five")==true)
		s= "5";
		else if(integarName.equals("six")==true)
		s= "6";
		else if(integarName.equals("seven")==true)
		s= "7";
		else if(integarName.equals("eight")==true)
		s= "8";
		else if(integarName.equals("nine")==true)
		s= "9";
		else 
			s = integarName;
										
		return s;
	}
	
	public String sepVar(Object obj)
	{
		String s="";
		if(obj instanceof IntegerRef)
		{
			IntegerRef ref;
			ref = (IntegerRef) obj; 
			s= filterIntegerNames(ref.getIntegerElem().getName());
		}
		
		if(obj instanceof IntegerVariableRef)
		{
			IntegerVariableRef ref;
			ref = (IntegerVariableRef) obj;
			s=filterIntegerNames(ref.getReferencedVar().getName());
		}
		return s;
	}
	
	public String buildActionBlocUnit (BinaryIntegerExpression in)
	{
		String l="";
		if(((in.getLeftValue() instanceof IntegerRef) || (in.getLeftValue() instanceof IntegerVariableRef)) && ((in.getRightValue() instanceof IntegerRef) || (in.getRightValue() instanceof IntegerVariableRef)))
			l = "("+ sepVar(in.getLeftValue())+ toStringSign(in)+sepVar(in.getRightValue()) +")";
		else if(((in.getLeftValue() instanceof IntegerRef) || (in.getLeftValue() instanceof IntegerVariableRef)) && (in.getRightValue() instanceof BinaryIntegerExpression))
			l = "("+ sepVar(in.getLeftValue())+ toStringSign(in)+buildActionBlocUnit((BinaryIntegerExpression) in.getRightValue()) +")";
		
		else if(((in.getRightValue() instanceof IntegerRef) || (in.getRightValue() instanceof IntegerVariableRef)) && (in.getLeftValue() instanceof BinaryIntegerExpression))
			l = "("+buildActionBlocUnit((BinaryIntegerExpression) in.getLeftValue())  + toStringSign(in)+sepVar(in.getRightValue())+")";
		
		else if ((in.getRightValue() instanceof BinaryIntegerExpression) && (in.getRightValue() instanceof BinaryIntegerExpression))
			l = "("+ buildActionBlocUnit((BinaryIntegerExpression) in.getLeftValue())+ toStringSign(in)+buildActionBlocUnit((BinaryIntegerExpression) in.getRightValue()) +")";
			return l;	
	}
	
	public String buildGuardBlocUnit(IntegerExpression left, IntegerExpression right, String exprSign)
	{
		String l = "";
		
		if(((left instanceof IntegerRef) || (left instanceof IntegerVariableRef)) && ((right instanceof IntegerRef) || (right instanceof IntegerVariableRef)))
			l = sepVar(left)+ exprSign + sepVar(right);
		else if(((left instanceof IntegerRef) || (left instanceof IntegerVariableRef)) && (right instanceof BinaryIntegerExpression))
			l = sepVar(left) + exprSign + buildActionBlocUnit((BinaryIntegerExpression) right);
		
		else if(((right instanceof IntegerRef) || (right instanceof IntegerVariableRef)) && (left instanceof BinaryIntegerExpression))
			l = buildActionBlocUnit((BinaryIntegerExpression) left)  + exprSign + sepVar(right);
		
		else if ((right instanceof BinaryIntegerExpression) && (right instanceof BinaryIntegerExpression))
			l = buildActionBlocUnit((BinaryIntegerExpression) left)+ exprSign + buildActionBlocUnit((BinaryIntegerExpression) right);
			
		return l;
	}
	public String toStringSign (BinaryIntegerExpression in)
	{
		String s="";
		if (in instanceof IntPlus)
		{
			s="+";
		}
		if (in instanceof IntMinus)
		{
			s="-";
		}
		if (in instanceof IntDivide)
		{
			s="/";
		}
		if (in instanceof IntMultiply)
		{
			s="*";
		}
		return s;
	}
	
	public String toStringGuard (ClassicalExpression cl) // ClassicalExpression
	{
		String s="";
		
		if (cl instanceof IntEqual) // IntEqual
		{
			IntEqual si;
			si = (IntEqual) cl;
			s = buildGuardBlocUnit(si.getLeftValue(), si.getRightValue(), " == ");
		}
		if (cl instanceof IntSup) // IntSup
		{
			IntSup si;
			si = (IntSup) cl;
			s = buildGuardBlocUnit(si.getLeftValue(), si.getRightValue(), " > ");
		}
		if (cl instanceof IntInf)	// IntInf
		{
			IntInf si;
			si = (IntInf) cl;
			s = buildGuardBlocUnit(si.getLeftValue(), si.getRightValue(), " < ");
		}

		if (cl instanceof IntInfEqual) // IntInfEqual
		{
			IntInfEqual si;
			si = (IntInfEqual) cl;
			s = buildGuardBlocUnit(si.getLeftValue(), si.getRightValue(), " <= ");
		}
		if (cl instanceof IntSupEqual)	// IntSupEqual
		{
			IntSupEqual si;
			si = (IntSupEqual) cl;
			s = buildGuardBlocUnit(si.getLeftValue(), si.getRightValue(), " >= ");
		}

		if (cl instanceof Not)	// Not
		{
			Not si;
			si = (Not) cl;
			ClassicalExpression br = (ClassicalExpression)si.getOperand(); 
			s= "!("+toStringGuard(br)+")";
		}

		if (cl instanceof Or)	// Or
		{
			Or si;
			si = (Or) cl;
			ClassicalExpression leftBr = (ClassicalExpression)si.getLeftValue();
			ClassicalExpression rightBr = (ClassicalExpression)si.getRightValue(); 
			s= "("+toStringGuard(leftBr)+")v("+toStringGuard(rightBr)+")";
		}

		if (cl instanceof And)	// And
		{
			And si;
			si = (And) cl;
			ClassicalExpression leftBr = (ClassicalExpression)si.getLeftValue();
			ClassicalExpression rightBr = (ClassicalExpression)si.getRightValue(); 
			s= "("+toStringGuard(leftBr)+")^("+toStringGuard(rightBr)+")";
		}

		if (cl instanceof Xor)	// Xor
		{
			Xor si;
			si = (Xor) cl;
			ClassicalExpression leftBr = (ClassicalExpression)si.getLeftValue();
			ClassicalExpression rightBr = (ClassicalExpression)si.getRightValue(); 
			s= "("+toStringGuard(leftBr)+")v_("+toStringGuard(rightBr)+")";
		}

		// if (cl instanceof BooleanRef)	// BooleanRef
		// if (cl instanceof SeqIsEmpty)	// SeqIsEmpty
		// if (cl instanceof BooleanVariableRef)	// BooleanVariableRef
		return s;
	}
	
	/**
	 * Create a label for a transition
	 * @param element  : the given transition
	 * @return "[sourceName]To[targetName]index"
	 */
	public String createLabel(Transition element){
		StringBuilder sb = new StringBuilder(16);
		sb.append(element.getSource().getName()).append("To").append(element.getTarget().getName());
		String text = sb.toString();
		int i =1;
		for (Iterator<Transition> iterator = ((StateMachineRelationDefinition)element.eContainer()).getTransitions().iterator(); iterator.hasNext();) {
			Transition t = iterator.next();
			if (t!=element) {
				if (t.getName().equalsIgnoreCase(text)) {
					i++;
				}
			}
		}
		if (i!=1) {
			sb.append(i);
		}				
		return sb.toString();
	}
	
	/**
	 * Compute state label
	 * @param state
	 * @return "({init}|{final}|{init, final})? [stateName]"
	 */
	public String computeLabel(State state){
		StringBuilder sb = new StringBuilder(16);
		if (((StateMachineRelationDefinition)state.eContainer()).getInitialStates().contains(state)) {
			sb.append("{init");
			if (((StateMachineRelationDefinition)state.eContainer()).getFinalStates().contains(state)) {
			sb.append(", final} ");
			}else {
				sb.append("} ");
			}
		}else {
			if (((StateMachineRelationDefinition)state.eContainer()).getFinalStates().contains(state)) {
				sb.append("{final} ");
			}
		}
		sb.append(state.getName());
		return sb.toString();
	}
	
	public String computeLabel(ConcreteEntity ce){
		StringBuilder sb = new StringBuilder(16);
		if (ce instanceof IntegerElement) {
			sb.append("Integer : ").append(((IntegerElement)ce).getName()).append(" = ").append(((IntegerElement)ce).getValue());
		}else {
			sb.append(ce.getName());
		}
		return sb.toString();
	}
}