//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.optaplanner.core.impl.domain.variable.inverserelation;

import org.optaplanner.core.impl.domain.variable.descriptor.VariableDescriptor;
import org.optaplanner.core.impl.domain.variable.listener.VariableListener;
import org.optaplanner.core.impl.score.director.ScoreDirector;

public class SingletonInverseVariableListener implements VariableListener<Object>, SingletonInverseVariableSupply {
  protected final InverseRelationShadowVariableDescriptor shadowVariableDescriptor;
  protected final VariableDescriptor sourceVariableDescriptor;

  public SingletonInverseVariableListener(InverseRelationShadowVariableDescriptor shadowVariableDescriptor, VariableDescriptor sourceVariableDescriptor) {
    this.shadowVariableDescriptor = shadowVariableDescriptor;
    this.sourceVariableDescriptor = sourceVariableDescriptor;
  }

  public void beforeEntityAdded(ScoreDirector scoreDirector, Object entity) {
  }

  public void afterEntityAdded(ScoreDirector scoreDirector, Object entity) {
    this.insert(scoreDirector, entity);
  }

  public void beforeVariableChanged(ScoreDirector scoreDirector, Object entity) {
    this.retract(scoreDirector, entity);
  }

  public void afterVariableChanged(ScoreDirector scoreDirector, Object entity) {
    this.insert(scoreDirector, entity);
  }

  public void beforeEntityRemoved(ScoreDirector scoreDirector, Object entity) {
    this.retract(scoreDirector, entity);
  }

  public void afterEntityRemoved(ScoreDirector scoreDirector, Object entity) {
  }

  protected void insert(ScoreDirector scoreDirector, Object entity) {
    Object shadowEntity = this.sourceVariableDescriptor.getValue(entity);
    if (shadowEntity != null) {
      Object shadowValue = this.shadowVariableDescriptor.getValue(shadowEntity);
      if (shadowValue != null) {
        // throw new IllegalStateException("The entity (" + entity + ") has a variable (" + this.sourceVariableDescriptor.getVariableName() + ") with value (" + shadowEntity + ") which has a sourceVariableName variable (" + this.shadowVariableDescriptor.getVariableName() + ") with a value (" + shadowValue + ") which is not null.\nVerify the consistency of your input problem for that sourceVariableName variable.");
      }

      scoreDirector.beforeVariableChanged(this.shadowVariableDescriptor, shadowEntity);
      this.shadowVariableDescriptor.setValue(shadowEntity, entity);
      scoreDirector.afterVariableChanged(this.shadowVariableDescriptor, shadowEntity);
    }

  }

  protected void retract(ScoreDirector scoreDirector, Object entity) {
    Object shadowEntity = this.sourceVariableDescriptor.getValue(entity);
    if (shadowEntity != null) {
      Object shadowValue = this.shadowVariableDescriptor.getValue(shadowEntity);
      if (shadowValue != entity) {
        // throw new IllegalStateException("The entity (" + entity + ") has a variable (" + this.sourceVariableDescriptor.getVariableName() + ") with value (" + shadowEntity + ") which has a sourceVariableName variable (" + this.shadowVariableDescriptor.getVariableName() + ") with a value (" + shadowValue + ") which is not that entity.\nVerify the consistency of your input problem for that sourceVariableName variable.");
      }

      scoreDirector.beforeVariableChanged(this.shadowVariableDescriptor, shadowEntity);
      this.shadowVariableDescriptor.setValue(shadowEntity, (Object)null);
      scoreDirector.afterVariableChanged(this.shadowVariableDescriptor, shadowEntity);
    }

  }

  public Object getInverseSingleton(Object planningValue) {
    return this.shadowVariableDescriptor.getValue(planningValue);
  }
}
