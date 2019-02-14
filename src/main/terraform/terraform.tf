resource "azurerm_network_security_rule" "8080" {
  provider = "azurerm.webapp"
  resource_group_name         = "${data.azurerm_network_security_group.default.resource_group_name}"
  network_security_group_name = "${data.azurerm_network_security_group.default.name}"
  name                        = "Nouss-Lab-HackatonLeaderboard-WebApp-8080"
  priority                    = 1088
  direction                   = "Inbound"
  access                      = "Allow"
  protocol                    = "Tcp"
  source_port_range           = "*"
  destination_port_range      = "8080"
  source_address_prefix       = "*"
  destination_address_prefix  = "*"
}

resource "azurerm_network_security_rule" "80" {
  provider = "azurerm.webapp"
  resource_group_name         = "${data.azurerm_network_security_group.default.resource_group_name}"
  network_security_group_name = "${data.azurerm_network_security_group.default.name}"
  name                        = "Nouss-Lab-HackatonLeaderboard-WebApp-80"
  priority                    = 1080
  direction                   = "Inbound"
  access                      = "Allow"
  protocol                    = "Tcp"
  source_port_range           = "*"
  destination_port_range      = "80"
  source_address_prefix       = "*"
  destination_address_prefix  = "*"
}
