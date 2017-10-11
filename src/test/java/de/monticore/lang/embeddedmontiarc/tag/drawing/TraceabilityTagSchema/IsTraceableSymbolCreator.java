/**
 *
 *  ******************************************************************************
 *  MontiCAR Modeling Family, www.se-rwth.de
 *  Copyright (c) 2017, Software Engineering Group at RWTH Aachen,
 *  All rights reserved.
 *
 *  This project is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 3.0 of the License, or (at your option) any later version.
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this project. If not, see <http://www.gnu.org/licenses/>.
 * *******************************************************************************
 */
/* generated from model null*/
/* generated by template templates.de.monticore.lang.montiarc.tagschema.SimpleTagTypeCreator*/


package de.monticore.lang.embeddedmontiarc.tag.drawing.TraceabilityTagSchema;

import de.monticore.lang.embeddedmontiarc.embeddedmontiarc._symboltable.ComponentSymbol;
import de.monticore.lang.montiarc.tagging._ast.ASTNameScope;
import de.monticore.lang.montiarc.tagging._ast.ASTScope;
import de.monticore.lang.montiarc.tagging._ast.ASTTag;
import de.monticore.lang.montiarc.tagging._ast.ASTTaggingUnit;
import de.monticore.lang.montiarc.tagging._symboltable.TagSymbolCreator;
import de.monticore.symboltable.Scope;
import de.se_rwth.commons.Joiners;
import de.se_rwth.commons.logging.Log;

import java.util.Optional;

/**
 * created by SimpleTagTypeCreator.ftl
 */
public class IsTraceableSymbolCreator implements TagSymbolCreator {

  public static Scope getGlobalScope(final Scope scope) {
    Scope s = scope;
    while (s.getEnclosingScope().isPresent()) {
      s = s.getEnclosingScope().get();
    }
    return s;
  }

  public void create(ASTTaggingUnit unit, Scope gs) {
    if (unit.getQualifiedNames().stream()
        .map(q -> q.toString())
        .filter(n -> n.endsWith("TraceabilityTagSchema"))
        .count() == 0) {
      return; // the tagging model is not conform to the TraceabilityTagSchema tagging schema
    }
    final String packageName = Joiners.DOT.join(unit.getPackage());
    final String rootCmp = // if-else does not work b/c of final (required by streams)
        (unit.getTagBody().getTargetModel().isPresent()) ?
            Joiners.DOT.join(packageName, ((ASTNameScope) unit.getTagBody().getTargetModel().get())
                .getQualifiedName().toString()) :
            packageName;

     for (ASTTag element : unit.getTagBody().getTags()) {
            element.getTagElements().stream()
              .filter(t -> t.getName().equals("IsTraceable"))
              .filter(t -> !t.getTagValue().isPresent()) // only marker tag with no value
              .forEachOrdered(t ->
                  element.getScopes().stream()
                    .filter(this::checkScope)
                    .map(s -> (ASTNameScope) s)
                    .map(s -> getGlobalScope(gs).<ComponentSymbol>resolveDown(
                        Joiners.DOT.join(rootCmp, s.getQualifiedName().toString()),
                        ComponentSymbol.KIND))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEachOrdered(s -> s.addTag(new IsTraceableSymbol())));
      }
    }

  protected boolean checkScope(ASTScope scope) {
    if (scope.getScopeKind().equals("NameScope")) {
      return true;
    }
    Log.error(String.format("0xT0005 Invalid scope kind: '%s'. IsTraceable expects as scope kind 'NameScope'.",
        scope.getScopeKind()), scope.get_SourcePositionStart());
    return false;
  }
}