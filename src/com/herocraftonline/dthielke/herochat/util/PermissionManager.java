/**
 * Copyright (C) 2011 DThielke <dave.thielke@gmail.com>
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send a letter to
 * Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 **/

package com.herocraftonline.dthielke.herochat.util;

import org.bukkit.entity.Player;

import com.nijiko.permissions.PermissionHandler;

public class PermissionManager {

    private PermissionHandler security;

    public PermissionManager(PermissionHandler security) {
        this.security = security;
    }

    public String getGroup(Player p) {
        if (security != null) {
            try {
            String world = p.getWorld().getName();
            String name = p.getName();
            String group = security.getGroup(world, name);
            if (group == null) {
                group = "";
            }
            return group;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public String getGroupPrefix(Player p) {
        if (security != null) {
            try {
                String world = p.getWorld().getName();
                String name = p.getName();
                String group = security.getGroup(world, name);
                String prefix = security.getGroupPrefix(world, group);
                if (prefix == null) {
                    prefix = "";
                }
                return prefix.replaceAll("&([0-9a-f])", "§$1");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }
    
    public String getGroupSuffix(Player p) {
        if (security != null) {
            try {
                String world = p.getWorld().getName();
                String name = p.getName();
                String group = security.getGroup(world, name);
                String suffix = security.getGroupSuffix(world, group);
                if (suffix == null) {
                    suffix = "";
                }
                return suffix.replaceAll("&([0-9a-f])", "§$1");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public String getPrefix(Player p) {
        if (security != null) {
            try {
                String world = p.getWorld().getName();
                String name = p.getName();
                String prefix = security.getUserPermissionString(world, name, "prefix");
                if (prefix == null || prefix.isEmpty()) {
                    String group = security.getGroup(world, name);
                    prefix = security.getGroupPrefix(world, group);
                    if (prefix == null) {
                        prefix = "";
                    }
                }
                return prefix.replaceAll("&([0-9a-f])", "§$1");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public String getSuffix(Player p) {
        if (security != null) {
            try {
                String world = p.getWorld().getName();
                String name = p.getName();
                String suffix = security.getUserPermissionString(world, name, "suffix");
                if (suffix == null || suffix.isEmpty()) {
                    String group = security.getGroup(world, name);
                    suffix = security.getGroupSuffix(world, group);
                    if (suffix == null) {
                        suffix = "";
                    }
                }
                return suffix.replaceAll("&([0-9a-f])", "§$1");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public boolean isAdmin(Player p) {
        if (security != null) {
            return security.has(p, "herochat.admin");
        } else {
            return true;
        }
    }

    public boolean isAllowedColor(Player p) {
        if (security != null) {
            return security.has(p, "herochat.color");
        } else {
            return true;
        }
    }

    public boolean canCreate(Player p) {
        if (security != null) {
            boolean admin = security.has(p, "herochat.admin");
            boolean create = security.has(p, "herochat.create");
            return admin || create;
        } else {
            return true;
        }
    }

}
