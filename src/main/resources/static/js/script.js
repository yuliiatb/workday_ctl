fetch('/employee')
    .then(response => response.json())
    .then(employees => {
      const tbody = document.getElementById('employee-tbody');
      employees.forEach(employee => {
        const row = `
          <tr onclick="openProfile(${employee.employeeId})">
            <td>${employee.employeeId}</td>
            <td>${employee.employeeFirstName}</td>
            <td>${employee.employeeLastName}</td>
            <td>${employee.employeeRole}</td>
          </tr>
        `;
        tbody.innerHTML += row;
      });
    });

    function openProfile(id) {
      fetch(`/employee/${id}`)
      .then(response => response.json())
      .then(employee => {
        document.getElementById('profile').innerHTML = `       
        <div class="profile-header" style="display: flex; align-items: center; justify-content: space-between;">
          <h2>Perfil de Empleado: ${employee.employeeRole}</h2>
          <button onclick="openReportPopup()">Generar informe</button>
        </div>

        <div class="row">
          <p><strong>ID:</strong> ${employee.employeeId}</p>
        </div>

        <p><strong>Nombre:</strong> ${employee.employeeFirstName}</p>
        <p><strong>Apellidos:</strong> ${employee.employeeLastName}</p>
        <p><strong>Horario:</strong> ${employee.scheduleId ? employee.scheduleId.scheduleId : "Horario no asignado"}</p>
        `;
        document.getElementById('profile').scrollIntoView({behavior: "smooth"});
        
        //Cargar los fichajes del empleado seleccionado
        showWorkLogs(employee.employeeId);
      });
    }

    //Función para mostrar los fichajes del empleado seleccionado.
    //El tiempo del inicio del descanso el la 1ª hora de salida, 
    // y el tiempo del fin del descanso el la 2ª hora de entrada
    function showWorkLogs(employee) {
      fetch(`/work_log/${employee}`)
      .then(response => response.json())
      .then(workLogs => {
        const tbody = document.getElementById('worklog-tbody');
        tbody.innerHTML = "";
        workLogs.forEach(workLog => {
        const row = `
        <tr>
            <td>${workLog.workDate ?? ''}</td>
            <td>${workLog.logStartTime ?? ''}</td>
            <td>${workLog.breakStart ?? ''}</td> 
            <td>${workLog.breakStart ?? ''}</td>
            <td>${workLog.breakEnd ?? ''}</td>
            <td>${workLog.breakEnd ?? ''}</td> 
            <td>${workLog.logEndTime ?? ''}</td>
          </tr>
        `; 
        tbody.innerHTML += row;
        }); 
      });
    }
  
    function openReportPopup() {
        document.getElementById('overlay').style.display = 'block';
        document.getElementById('reportPopup').style.display = 'block';
    }
  
    function closeReportPopup() {
        document.getElementById('overlay').style.display = 'none';
        document.getElementById('reportPopup').style.display = 'none';
    }   