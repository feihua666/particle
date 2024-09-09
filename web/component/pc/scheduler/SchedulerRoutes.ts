import schedulerTempTaskAdminRoutes from "./views/temptask/admin/schedulerTempTaskAdminRoutes";
import schedulerTempTaskRunRecordAdminRoutes from "./views/temptask/admin/schedulerTempTaskRunRecordAdminRoutes";
import schedulerTempTaskRunRecordLogAdminRoutes from "./views/temptask/admin/schedulerTempTaskRunRecordLogAdminRoutes";
import schedulerExecuteRecordAdminRoutes from "./views/schedule/admin/schedulerExecuteRecordAdminRoutes";
import scheduleAdminRoutes from "./views/schedule/admin/scheduleAdminRoutes";
import scheduleJobAdminRoutes from "./views/schedule/admin/scheduleJobAdminRoutes";
import scheduleTriggerAdminRoutes from "./views/schedule/admin/scheduleTriggerAdminRoutes";

const SchedulerRoutes = []
    .concat(schedulerTempTaskAdminRoutes)
    .concat(schedulerTempTaskRunRecordAdminRoutes)
    .concat(schedulerTempTaskRunRecordLogAdminRoutes)
    .concat(schedulerExecuteRecordAdminRoutes)
    .concat(scheduleAdminRoutes)
    .concat(scheduleJobAdminRoutes)
    .concat(scheduleTriggerAdminRoutes)
export default SchedulerRoutes