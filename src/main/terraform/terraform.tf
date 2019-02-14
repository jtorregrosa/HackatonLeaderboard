resource "azurerm_network_security_rule" "default" {
  provider = "azurerm.webapp"
  resource_group_name         = "${data.azurerm_network_security_group.default.resource_group_name}"
  network_security_group_name = "${data.azurerm_network_security_group.default.name}"
  name                        = "Nouss-Lab-HackatonLeaderboard-WebApp"
  priority                    = 1080
  direction                   = "Inbound"
  access                      = "Allow"
  protocol                    = "Tcp"
  source_port_range           = "*"
  destination_port_range      = "8080"
  source_address_prefix       = "*"
  destination_address_prefix  = "*"
}
