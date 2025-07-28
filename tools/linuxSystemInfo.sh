#!/usr/bin/env bash

# 获取系统信息函数
get_system_info() {
    local os_name
    local os_version
    local model
    local serial
    local cpu_info
    local cpu_cores
    local cpu_speed
    local mem_total
    local disk_info
    local gpu_info

    # 检测操作系统类型
    case "$(uname -s)" in
        Linux*)
            # 获取发行版信息
            if [ -f /etc/os-release ]; then
                os_name=$(grep PRETTY_NAME /etc/os-release | cut -d'"' -f2)
            elif [ -f /etc/redhat-release ]; then
                os_name=$(cat /etc/redhat-release)
            else
                os_name="Unknown Linux"
            fi

            # 获取硬件型号
            if [ -f /sys/devices/virtual/dmi/id/product_name ]; then
                model=$(tr -d '\0' < /sys/devices/virtual/dmi/id/product_name)
            elif command -v dmidecode &> /dev/null; then
                model=$(sudo dmidecode -s system-product-name 2>/dev/null)
            else
                model="Unknown"
            fi

            # 获取序列号
            if [ -f /sys/devices/virtual/dmi/id/product_serial ]; then
                serial=$(tr -d '\0' < /sys/devices/virtual/dmi/id/product_serial | head -n1)
            elif command -v dmidecode &> /dev/null; then
                serial=$(sudo dmidecode -s system-serial-number 2>/dev/null | head -n1)
            else
                serial="Unknown"
            fi

            # 获取CPU信息
            cpu_info=$(lscpu | grep "Model name" | cut -d':' -f2 | sed 's/^ *//; s/  */ /g')
            cpu_cores=$(lscpu | grep "^CPU(s):" | awk '{print $2}')
            cpu_speed=$(lscpu | grep "MHz" | awk '{print $3}' | head -n1 | awk '{printf "%.2f GHz", $1/1000}')

            # 获取内存信息
            mem_total=$(grep MemTotal /proc/meminfo | awk '{printf "%.1f GB", $2/1024/1024}')

            # 获取磁盘信息
            disk_info=$(df -h / | awk 'NR==2 {print $2 " total, " $4 " available"}')

            # 获取显卡信息
            gpu_info=$(lspci | grep -i "vga\|3d\|display" | cut -d':' -f3- | sed 's/^ *//' | head -n1)
            ;;

        Darwin*)
            # macOS系统
            os_name="macOS $(sw_vers -productVersion)"
            model=$(sysctl -n hw.model)
            serial=$(system_profiler SPHardwareDataType | awk '/Serial/ {print $4}')

            cpu_info=$(sysctl -n machdep.cpu.brand_string)
            cpu_cores=$(sysctl -n hw.ncpu)
            cpu_speed=$(sysctl -n hw.cpufrequency | awk '{printf "%.2f GHz", $1/1000000000}')

            mem_total=$(sysctl -n hw.memsize | awk '{printf "%.1f GB", $1/1024/1024/1024}')

            disk_info=$(df -h / | awk 'NR==2 {print $2 " total, " $4 " available"}')

            gpu_info=$(system_profiler SPDisplaysDataType | awk -F': ' '/Chipset Model/ {print $2}' | head -n1)
            ;;

        *)
            echo "Unsupported operating system"
            exit 1
            ;;
    esac

    # 输出系统信息
    echo "=============================="
    echo "     System Configuration"
    echo "=============================="
    printf "%-12s: %s\n" "OS" "${os_name}"
    printf "%-12s: %s\n" "Model" "${model}"
    printf "%-12s: %s\n" "Serial" "${serial}"
    printf "%-12s: %s (%d cores)\n" "Processor" "${cpu_info}" "${cpu_cores}"
    printf "%-12s: %s\n" "Speed" "${cpu_speed}"
    printf "%-12s: %s\n" "Memory" "${mem_total}"
    printf "%-12s: %s\n" "Storage" "${disk_info}"
    printf "%-12s: %s\n" "Graphics" "${gpu_info}"
    echo "=============================="
}

# 执行主函数
get_system_info
