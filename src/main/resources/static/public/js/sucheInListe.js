  function suchFunctionPerson() {
                      // Deklarieren der Variablen
                      var input, filter, table, tr, i, j, column_length, count_td;
                      column_length = document.getElementById('listPersonTabelle').rows[0].cells.length;
                      input = document.getElementById("inputSuchePersonen");
                      filter = input.value.toLowerCase();
                      table = document.getElementById("listPersonTabelle");
                      tr = table.getElementsByTagName("tr");

                        // Schleife durch alle Zeilen der Tabelle und blendet alle aus, die nicht zur such-query passen
                        for (i = 1; i < tr.length; i++) { // außer erster (heading) zeile
                        count_td = 0;
                        for(j = 1; j < column_length; j++){ // außer erster spalte, dafür, _length -1 entfernt damit email dabei
                            td = tr[i].getElementsByTagName("td")[j];
                            /* hier spalten hinzufügen, auf die der filter angewendet werden soll */
                            if (td) {
                              if ( td.innerHTML.toLowerCase().indexOf(filter) > -1)  {
                                count_td++;
                              }
                            }
                        }
                        if(count_td > 0){
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                      }

                    }



 function suchFunctionKurs() {
                      // Deklarieren der Variablen
                      var input, filter, table, tr, i, j, column_length, count_td;
                      column_length = document.getElementById('listPersonTabelle').rows[0].cells.length;
                      input = document.getElementById("inputSucheKurse");
                      filter = input.value.toLowercase();
                      table = document.getElementById("listKurseTabelle");
                      tr = table.getElementsByTagName("tr");

                      // Schleife durch alle Zeilen der Tabelle und blendet alle aus, die nicht zur such-query passen
                      for (i = 1; i < tr.length; i++) { // außer erster (heading) zeile
                        count_td = 0;
                        for(j = 1; j < column_length; j++){ // außer erster spalte, _length -1 entfernt damit letzte spalte dabei
                            td = tr[i].getElementsByTagName("td")[j];
                            /* hier spalten hinzufügen, auf die der filter angewendet werden soll */
                            if (td) {
                              if ( td.innerHTML.toLowerCase().indexOf(filter) > -1)  {
                                count_td++;
                              }
                            }
                        }
                        if(count_td > 0){
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                      }

                    }