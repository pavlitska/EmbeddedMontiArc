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
package de.monticore.lang.embeddedmontiarc.tag.drawing;//package de.monticore.lang.montiarc.tag.drawing;
//
//import java.util.Optional;
//import java.util.regex.Pattern;
//
//import de.monticore.lang.montiarc.montiarc._symboltable.ConnectorSymbol;
//import de.monticore.lang.montiarc.tagging._ast.ASTConnectorScope;
//import de.monticore.lang.montiarc.tagging._ast.ASTNameScope;
//import de.monticore.lang.montiarc.tagging._ast.ASTRegexTag;
//import de.monticore.lang.montiarc.tagging._ast.ASTTagElement;
//import de.monticore.lang.montiarc.tagging._ast.ASTTaggingUnit;
//import de.monticore.lang.montiarc.tagging._ast.ASTTargetElement;
//import de.monticore.lang.montiarc.tagging._symboltable.TagSymbolCreator;
//import de.monticore.symboltable.Scope;
//import de.se_rwth.commons.Joiners;
//
///**
// * Created by MichaelvonWenckstern on 04.06.2016.
// */
//public class ConnectorLayoutSymbolCreator implements TagSymbolCreator {
//
//  /**
//   * regular expression pattern:
//   * id = {id} , pos = ({x} , {y}) , endVal = ({endX} , {endY}) , mid = ({midX} , {midY})
//   * \s*id\s*=\s*([1-9]\d*)\s*,\s*pos\s*=\s*\(([1-9]\d*)\s*,\s*([1-9]\d*)\)\s*,
//   * \s*endVal\s*=\s*\(([1-9]\d*)\s*,\s*([1-9]\d*)\)\s*,\s*mid\s*=\s*\(([1-9]\d*)\s*,\s*([1-9]\d*)\)\s*
//   * at http://www.regexplanet.com/advanced/java/index.html
//   * *
//   */
//  public static final Pattern pattern = Pattern.compile(
//      "\\[\\s*id\\s*=\\s*([1-9]\\d*)\\s*,\\s*pos\\s*=\\s*\\(([1-9]\\d*)\\s*,"
//          + "\\s*([1-9]\\d*)\\)\\s*,\\s*endVal\\s*=\\s*\\(([1-9]\\d*)\\s*,"
//          + "\\s*([1-9]\\d*)\\)\\s*,\\s*mid\\s*=\\s*\\(([1-9]\\d*)\\s*,"
//          + "\\s*([1-9]\\d*)\\)\\s*\\]");
//
//  public static Scope getGlobalScope(final Scope scope) {
//    Scope s = scope;
//    while (s.getEnclosingScope().isPresent()) {
//      s = s.getEnclosingScope().get();
//    }
//    return s;
//  }
//
//  public void create(ASTTaggingUnit unit, Scope gs) {
//    if (unit.getQualifiedNames().stream().map(q -> q.toString())
//        .filter(n -> n.endsWith("LayoutTagSchema")).count() == 0) {
//      return; // the tagging model is not conform to the traceability tagging schema
//    }
//    final String packageName = Joiners.DOT.join(unit.getPackage());
//    final String rootCmp = // if-else does not work b/c of final (required by streams)
//        (unit.getTagBody().getTargetModel().isPresent()) ? Joiners.DOT.join(packageName, ((ASTNameScope) unit.getTagBody().getTargetModel().get()).getQualifiedName().toString()) : packageName;
//
//    for (final ASTTagElement element : unit.getTagBody().getTagElements()) {
//      if (element instanceof ASTTargetElement) {
//
//        ((ASTTargetElement) element).getTags().stream()
//            .filter(t -> t.getName().equals("ConnectorLayout")) // after that we can do error handling
//            .filter(t -> t instanceof ASTRegexTag)
//            .map(t -> (ASTRegexTag) t)
//            .map(r -> pattern.matcher(r.getRegex()))
//            .filter(m -> m.matches())
//            .forEachOrdered(m ->
//                ((ASTTargetElement) element).getScopes().stream()
//                    .filter(s -> s instanceof ASTConnectorScope) // extends ASTNameScope maybe just overwrite there the method --> is then easier for later generator
//                    .map(s -> (ASTConnectorScope) s)
//                    .map(s -> getGlobalScope(gs).<ConnectorSymbol>
//                        resolveDown(Joiners.DOT.join(rootCmp, // resolve down does not try to reload symbol
//                        s.getQualifiedName().toString()), ConnectorSymbol.KIND))
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .forEachOrdered(s -> s.addTag(
//                        new ConnectorLayoutSymbol(
//                            Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)),
//                            Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)),
//                            Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)),
//                            Integer.parseInt(m.group(7)))))
//            );
//      }
//    }
//  }
//}
