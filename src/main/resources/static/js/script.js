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
    
    //Cargar los datos sobre los fichajes y ausencias del empleado seleccionado.
    showEmployeeWorkData(employee.employeeId);
  });
}

// Función para mostrar los fichajes y las ausencias del empleado seleccionado.
async function showEmployeeWorkData(employeeId) {
  try {
    const response = await fetch(`/work_log/${employeeId}`);
    if (!response.ok) throw new Error(`HTTP error: ${response.status}`);

    const workLogs = await response.json();
    
    // Obtener WorkLog de un emleado 
    const worklogBody = document.getElementById('worklog-tbody');
    worklogBody.innerHTML = "";

    if (!Array.isArray(workLogs) || workLogs.length === 0) {
      worklogBody.innerHTML = `<tr><td colspan="5">No hay fichajes disponibles</td></tr>`;
    } else {
      workLogs.forEach(log => {
        const row = `
          <tr>
            <td>${log.date ?? 'No se han registrado fichajes'}</td>
            <td>${log.logStartTime ?? '–'}</td>            
            <td>${log.breakStart ?? '–'}</td>
            <td>${log.breakEnd ?? '–'}</td>            
            <td>${log.logEndTime ?? '–'}</td>
          </tr>
        `;
        worklogBody.insertAdjacentHTML('beforeend', row);
      });
    }

    // Obtener LevaLog del mismo empleado
    const reportBody = document.getElementById('report-tbody');
    reportBody.innerHTML = "";

    if (!Array.isArray(workLogs) || workLogs.length === 0) {
      reportBody.innerHTML = `<tr><td colspan="6">No hay datos disponibles</td></tr>`;
    } else {
      workLogs.forEach(log => {
        const row = `
          <tr>
            <td>${log.date ?? 'No se han registrado ausencias'}</td>
            <td>${log.totalHours ?? '–'}</td>
            <td>${log.absenceHours ?? '–'}</td>
            <td>${log.leaveType ?? '–'}</td>
            <td>${log.extraHours ?? '–'}</td>
            <td>${log.hoursLeft ?? '–'}</td>
          </tr>
        `;
        reportBody.insertAdjacentHTML('beforeend', row);
      });
    }

  } catch (error) {
    console.error("Error al cargar los datos del empleado: ", error);
    document.getElementById('worklog-tbody').innerHTML =
      `<tr><td colspan="5">Error al cargar fichajes</td></tr>`;
    document.getElementById('report-tbody').innerHTML =
      `<tr><td colspan="6">Error al cargar el informe de horas</td></tr>`;
  }
}

function openReportPopup() {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('reportPopup').style.display = 'block';
}

function closeReportPopup() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('reportPopup').style.display = 'none';
}   